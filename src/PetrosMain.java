import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetrosMain
{
    public static List<Car> cars = new ArrayList<>();
    public static List<Ride> rides;
    public static Data info;
    public static long finalScore;

    public static void main(String[] args)
    {
        info = Parser.readFromFile();
        rides = info.rides;

        for (int i=0;i<info.numOfVehicles;++i){
            cars.add(new Car());
        }

        boolean flag = true;
        while(flag)
        {
            flag = false;
            for (Car currentCar : cars) {
                if (currentCar.steps <= info.maxNumOfSteps) {
                    findAvailableRides(rides, currentCar);
                    if (currentCar.currentCandidateRides.isEmpty()) {
                        continue;
                    }

                    boolean hasRideAssigned = assignBestRideWithBonus(currentCar);
                    if (!hasRideAssigned) {
                        assignFromRemainingRides(currentCar);
                    }
                    flag = true;
                    currentCar.currentCandidateRides.clear();
                }
            }
        }
        System.out.println("Score: "+finalScore);
        //MyWriter.writeToFile();
    }



    //CAR - RIDE INTERACTION METHODS



    private static void findAvailableRides(List<Ride> rides, Car car)
    {
        for(Ride ride : rides)
        {
            //c:16 , b:2 , d:1 , s:7.5
            if(calculateCarProximity(car,ride) < ((info.rows + info.columns)/7.5)
                    && determineIfCarFinishesOnTime(car,ride)){
                car.currentCandidateRides.add(ride);
            }
        }
    }

    private static void moveToNewLocation(Car car)
    {
        Random rd = new Random();
        int curX = car.position.x; int curY = car.position.y;
        int offsetX = (int)(info.columns/8) + 1; int offsetY = (int)(info.rows/8)+ 1;

        int randX, randY;
        do {
             randX = rd.nextInt(2 * offsetX) - offsetX;
             randY = rd.nextInt(2 * offsetY) - offsetY;
        }while(randX + curX < info.columns && randX + curX >= 0 &&
               randY + curY < info.rows && randY + curY >= 0);

        car.setNewPosition(randX + curX, randY + curY);
    }

    private static boolean assignBestRideWithBonus(Car car)
    {
        Ride bestAvailableRide = null;
        int bestRidePoints = 0;

        for(Ride ride : car.currentCandidateRides)
        {
            if(determineIfBonusIsAvailable(car, ride))
            {
                if(calculateRidePoints(ride) > bestRidePoints){
                    bestAvailableRide = ride;
                    bestRidePoints = calculateRidePoints(ride);
                }
            }
        }

        if(bestAvailableRide == null){
            return false;
        } else{
            car.addRide(bestAvailableRide);
            finalScore += bestRidePoints + info.bonus;
            rides.remove(bestAvailableRide);
        }
        return true;
    }

    private static void assignFromRemainingRides(Car car)
    {
        Ride bestAvailableRide = null;
        int bestRidePoints = 0;

        for(Ride ride : car.currentCandidateRides)
        {
            if(determineIfCarFinishesOnTime(car, ride))
            {
                if(calculateRidePoints(ride) > bestRidePoints){
                    bestAvailableRide = ride;
                    bestRidePoints = calculateRidePoints(ride);
                }
            }
        }

        if(bestAvailableRide != null) {
            car.addRide(bestAvailableRide);
            finalScore += bestRidePoints;
            rides.remove(bestAvailableRide);
        }
    }



    //CALCULATION METHODS



    private static int calculateRidePoints(Ride ride)
    {
        return Math.abs(ride.finishIntersection.x - ride.startIntersection.x) +
                Math.abs(ride.finishIntersection.y - ride.startIntersection.y);
    }


    private static int calculateCarProximity(Car car, Ride ride)
    {
        return Math.abs(car.position.x - ride.startIntersection.x) + Math.abs(car.position.y - ride.startIntersection.y);
    }

    private static boolean determineIfBonusIsAvailable(Car car, Ride ride)
    {
        return (calculateCarProximity(car, ride) + car.steps <= ride.earliestStart);
    }

    private static boolean determineIfCarFinishesOnTime(Car car, Ride ride)
    {
        return (calculateRideDistance(ride) + calculateCarProximity(car, ride) + car.steps <= ride.latestFinish)
                && (calculateRideDistance(ride) + calculateCarProximity(car, ride) + car.steps <= info.maxNumOfSteps);
    }

    private static int calculateRideDistance(Ride ride)
    {
        return Math.abs(ride.startIntersection.x - ride.finishIntersection.x) +
                Math.abs(ride.startIntersection.y - ride.finishIntersection.y);
    }


}

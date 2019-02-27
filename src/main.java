import java.util.ArrayList;
import java.util.List;

class main {
	public static List<Car> cars = new ArrayList();
	public static List<Ride> rides;
	
	
	public static void main(String[] argc){
		Data info = Parser.readFromFile();
		rides = info.rides;
		
		for (int i=0;i<info.numOfVehicles;++i){
			cars.add(new Car());
		}

		
		//Algorithm
		outerLoop:
		for(Car currentCar : cars){
			while(currentCar.steps <= info.maxNumOfSteps){
				if(rides.size() == 0 ) break outerLoop;
				Ride bestRide = getBestRideCandidate(currentCar);
				if(calculateDinstance(bestRide) + calculateProximity(currentCar, bestRide) + currentCar.steps < info.maxNumOfSteps){
					currentCar.addRide(bestRide);
					rides.remove(bestRide);
				}else{
					break;
				}
			}
		}
		System.out.println("hey world");
		info = Parser.readFromFile();
		

		MyWriter.writeToFile();
	}
	
	
	private static Ride getBestRideCandidate(Car car){
		ArrayList<Double> score = new ArrayList();

		for (Ride currentRide : rides){
			score.add(gradingMethod(car, currentRide));
		}
		
		double max = -1;
		int max_index = -1;
		
		for (int i=0; i< score.size(); ++i){
			if(max <= score.get(i)){
				max = score.get(i);
				max_index = i;
			}
		}
		
		
		return rides.get(max_index);
		
		
		
	}
	
	
	public static int calculateDinstance(Ride ride){
		return Math.abs(ride.startIntersection.x - ride.finishIntersection.x) + Math.abs(ride.startIntersection.y - ride.finishIntersection.y);
	}
	
	public static int calculateProximity(Car car, Ride ride){
		return Math.abs(car.position.x - ride.startIntersection.x) + Math.abs(car.position.y - ride.startIntersection.y);
	}
	
	
	private static double gradingMethod(Car car, Ride ride){
		return (double)calculateDinstance(ride)/(calculateProximity(car, ride) + 1 + Math.abs(ride.earliestStart - car.steps)*5);
		
		
	}
	
}

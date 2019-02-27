import java.util.ArrayList;
import java.util.List;

public class Car
{
    public Point position;
    public int steps;
    public List<Ride> rides;
    public List<Ride> currentCandidateRides;
    public List<Integer> rideIndexes;

    public Car()
    {
        this.position = new Point();
        this.steps = 0;
        rides = new ArrayList<>();
        currentCandidateRides = new ArrayList<>();
    }


    public void addRide(Ride ride)
    {
        rides.add(ride);
        steps += main.calculateDinstance(ride) + main.calculateProximity(this, ride);
        position = ride.finishIntersection;
    }

    public void setNewPosition(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;
        steps += x + y;
    }

}

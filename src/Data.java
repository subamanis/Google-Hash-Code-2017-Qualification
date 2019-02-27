import java.util.ArrayList;
import java.util.List;

public class Data
{
    public int rows;
    public int columns;
    public int numOfVehicles;
    public int numOfRides;
    public int maxNumOfSteps;
    public int bonus;
    public List<Ride> rides;

    public Data(int rows, int columns, int numOfVehicles, int numOfRides,
                int maxNumOfSteps, int bonus, ArrayList<Ride> rides)
    {
        this.rows = rows;
        this.columns = columns;
        this.numOfVehicles = numOfVehicles;
        this.numOfRides = numOfRides;
        this.maxNumOfSteps = maxNumOfSteps;
        this.bonus = bonus;
        this.rides = rides;
    }

    public String toString(){
        return ("Rows: "+ rows+"  Columns: "+columns+"  Number of vehicles: "+
                numOfVehicles+ "  Number of rides: "+numOfRides+
                "  Bonus: "+ bonus+"  maximum number of steps: "+
                maxNumOfSteps+"\n Rides: "+rides.toString());
    }
}

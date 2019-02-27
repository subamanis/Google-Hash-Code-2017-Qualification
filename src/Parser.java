import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
    static int rows;
    static int columns;
    static int numOfVehicles;
    static int numOfRides;
    static int maxNumOfSteps;
    static int bonus;
    static ArrayList<Ride> rides = new ArrayList<>();
    static int rideCounter;

    public static Data readFromFile()
    {
        String fileName = "s.in";

        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line = br.readLine();
            fillFirstLine(line);

            while((line = br.readLine()) != null)
            {
                if(!line.equals("")){
                    createRide(line);
                }
            }
        }catch(IOException e){
            System.out.println("You fucked up");
        }

        return new Data(rows, columns, numOfVehicles, numOfRides, maxNumOfSteps, bonus, rides);
    }

    private static void fillFirstLine(String line)
    {
        String[] firstLine = line.split(" ");

        rows = Integer.parseInt(firstLine[0]);
        columns = Integer.parseInt(firstLine[1]);
        numOfVehicles = Integer.parseInt(firstLine[2]);
        numOfRides = Integer.parseInt(firstLine[3]);
        bonus = Integer.parseInt(firstLine[4]);
        maxNumOfSteps = Integer.parseInt((firstLine[5]));
    }

    private static void createRide(String line)
    {
        String[] rideInfo = line.split(" ");
        int startX = Integer.parseInt(rideInfo[0]);
        int startY = Integer.parseInt(rideInfo[1]);
        int finishX = Integer.parseInt(rideInfo[2]);
        int finishY = Integer.parseInt(rideInfo[3]);
        int earliestStart = Integer.parseInt(rideInfo[4]);
        int latestStart = Integer.parseInt(rideInfo[5]);

        rides.add(new Ride(new Point(startX,startY), new Point(finishX,finishY), earliestStart,
                                                        latestStart, rideCounter++));
    }
}

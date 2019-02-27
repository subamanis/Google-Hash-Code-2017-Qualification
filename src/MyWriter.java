import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyWriter
{
    static String fileName = "test.txt";

    public static void writeToFile()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for(int i=0; i<main.cars.size(); i++)
            {
                bw.write(i+" ");
                for(Ride r : main.cars.get(i).rides)
                {
                    bw.write(r.ID+" ");
                }
                bw.write("\n");
            }

        }catch(IOException e){
            System.out.println("You fucked up");
        }
    }
}

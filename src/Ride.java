public class Ride
{
    Point startIntersection;
    Point finishIntersection;
    int earliestStart;
    int latestFinish;
    int ID;

    public Ride(Point startIntersection, Point finishIntersection, int earliestStart, int latestFinish, int ID)
    {
        this.startIntersection = startIntersection;
        this.finishIntersection = finishIntersection;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
        this.ID = ID;
    }

    public String toString()
    {
        return ("Starts from: "+startIntersection+" with destination: "+finishIntersection
                +". The earliest start is: "+earliestStart+". The latest start is: "+ latestFinish +"\n");
    }
}

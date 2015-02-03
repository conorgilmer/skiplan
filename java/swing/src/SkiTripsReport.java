//import java.util.*;

public class SkiTripsReport
{
        SkiTripsReport( int    trips,
                        double avgFlights, 
                        double avgTransfers,
                        double avgAccom,
                        double avgSkipass,
                        double avgSkihire,
                        double avgCost) 
        {
                this.trips        = trips;
                this.avgFlights   = avgFlights;
                this.avgTransfers = avgTransfers;
                this.avgAccom     = avgAccom;
                this.avgSkipass   = avgSkipass;
                this.avgSkihire   = avgSkihire;
                this.avgCost      = avgCost;
        }

        int    trips; 
        double avgFlights; 
        double avgTransfers;
        double avgAccom;
        double avgSkipass;
        double avgSkihire;
        double avgCost; 
 
        public int    getTrips()        { return trips;} 
        public double getAvgFlights()   { return avgFlights;} 
        public double getAvgTransfers() { return avgTransfers;} 
        public double getAvgAccom()     { return avgAccom;} 
        public double getAvgSkipass()   { return avgSkipass;} 
        public double getAvgSkihire()   { return avgSkihire;} 
        public double getAvgCost()      { return avgCost;} 

        public String toString()
        {
                return "Ski Holiday Averages Summary" + "\n" +
                "Number of Trips        = " + trips + "\n" +
                "Average Flights        = " + avgFlights + "\n" +
                "Average Transfers      = " + avgTransfers + "\n" +
                "Average Accommodation  = " + avgAccom + "\n" +
                "Average Ski Hire       = " + avgSkihire + "\n" +
                "Average Ski Pass       = " + avgSkipass + "\n" +
                "Average Cost           = " + avgCost + "\n" ;
        }
}


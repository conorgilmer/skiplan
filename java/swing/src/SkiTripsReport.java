//import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } /* end of SkiTripsReport */

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

        /* display the contents in a report for display and writing to a file */
        public String toString()
        {
        	//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        	    String timeStamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(new Date());
                return "Ski Holiday Averages Summary" + "\n" +
        		"Generated on " + timeStamp + "\n" +
                "Number of Trips        = " + trips + "\n" +
                "Average Flights        = " + floattoeuro(avgFlights) + "\n" +
                "Average Transfers      = " + floattoeuro(avgTransfers) + "\n" +
                "Average Accommodation  = " + floattoeuro(avgAccom) + "\n" +
                "Average Ski Hire       = " + floattoeuro(avgSkihire) + "\n" +
                "Average Ski Pass       = " + floattoeuro(avgSkipass) + "\n" +
                "Average Cost           = " + floattoeuro(avgCost) + "\n" ;
        } /* and of toString() */
        
        /* float to euro convert float to string currency formatted for displaying on screen*/
        public String floattoeuro(double avgCost2){
        	
        		String str = String.format("%.02f euro", avgCost2);
        		return str;
        
        } /* end of floattoeuro */
        
}


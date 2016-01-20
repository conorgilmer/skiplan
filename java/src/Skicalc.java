/* Skicalc.java 
 * calculate the costs for a skiholiday 
 * write the costs to a file for future reference
 * display a list of previous ski trips save to this file
 * by Conor Gilmer (conor.gilmer@gmail.com)
 */

package conors;

import java.util.Scanner;
import java.io.*;
//import java.util.String;

public final class Skicalc {
	/* initialise variables */
	public static String location      = "";
	public static int days             = 0;
	public static double dailyspend    = 0;
	public static double weeklyspend   = 0;
	public static double total         = 0;
	public static double flights       = 0;
	public static double connection    = 0;
	public static double accommodation = 0;
	public static double skipass       = 0;
	public static double skihire       = 0;
	public static double lessons       = 0;
	public static double insurance     = 0;
	public static String outputFile    = "skidata.txt";
 
	/* display a line of text to screen */
	private static void display(String text ) {
		System.out.println(text);
	} /* end of display */

	/* display a double number */
	private static void displayf(double num) {
		System.out.format("%.2f", num);
	} /* end of displayf */

        /* get the cost as to a question return the amount entered*/
	private static double getCost(String quest)
	{
		Scanner input = new Scanner(System.in);
		display(quest);
		String sInput = input.next();;
		return  Double.parseDouble(sInput); //return a double
		
	} /* end of getCost */	
        
	/* get the description/location entered*/
	private static String getDescription(String quest)
	{
		Scanner input = new Scanner(System.in);
		display(quest);
		String sInput = input.next();;
		return sInput;
		
	} /* end of getCost */	

	/* write a string to a file, if file exists append string to file. */
	private static void writeToFile(String str, String filename) {
      		try {
            		File dataFile   = new File(filename);
			PrintWriter out = null;
			if ( dataFile.exists() && !dataFile.isDirectory() ) {
				out = new PrintWriter(new FileOutputStream(new File(filename), true));
				out.append(str);
				out.close();
				display("\nRow added to file "+filename+"\n");
			} 
			else {
				out = new PrintWriter(filename);
				out.println(str);
				out.close();
				display("\nNew file "+filename+ " created and row added.\n");
			}
		} catch (IOException iox) {
            		//do stuff with exception
        	    	iox.printStackTrace();
        	}
	} /* end of writeToFile */

	/* read from a file and print on screen */
	private static void readFile(String filename) {
		try {
            		File dataFile         = new File(filename);
			FileReader fileReader = new FileReader(dataFile);
			BufferedReader reader = new BufferedReader(fileReader);
			//BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    			String line = null;
			display("Contents of " + filename + "\n");
                    	     display("Resort\tFlights\tConnection\tAccommodation\tPass\tPack\tTotal");
    			while ((line = reader.readLine()) != null) {
        //			display(line);
			     String []  s = line.split(",");
                    	     display(s[0] +"\t"+ s[1] +"\t"+s[2] +"\t" +s[3] +"\t" +s[4] +"\t" +s[5]+"\t"+s[11]);

    			}
			display("\nEnd of " + filename + "\n");
		} catch (IOException x) {
    			System.err.format("IOException: %s%n", x);
		}
	} /* end of readFile */


	/* read from a file and print dtats on screen */
	private static void readandcalcFile(String filename) {
		double avgFlights   = 0;
		double avgTransfers = 0;
		double avgAccom     = 0;
		double avgSkipass   = 0;
		double avgSkihire   = 0;
		double avgTotals    = 0;
		int    trips        = 0;
		try {
            		File dataFile         = new File(filename);
			FileReader fileReader = new FileReader(dataFile);
			BufferedReader reader = new BufferedReader(fileReader);
			//BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    			String line = null;
			display("Contents of " + filename + "\n");
    			while ((line = reader.readLine()) != null) {
        //			display(line);
				trips++;
			     String []  s = line.split(",");
                    	     display(s[0] +"\t"+ s[1] +"\t"+s[2] +"\t" +s[3] +"\t" +s[4] +"\t" +s[5]+"\t"+s[11]);
			avgFlights   = avgFlights + Double.valueOf(s[1]);
			avgTransfers = avgTransfers + Double.valueOf(s[2]);
			avgAccom = avgAccom + Double.valueOf(s[3]);
			avgSkipass = avgSkipass + Double.valueOf(s[4]);
			avgSkihire = avgSkihire + Double.valueOf(s[5]);
			avgTotals = avgTotals + Double.valueOf(s[11]);
    			}
			display("\n Averages = " + floattoeuro(avgFlights/trips) + "\t" +floattoeuro(avgTransfers/trips) +"\t" +floattoeuro(avgAccom/trips) + "\t" + floattoeuro(avgSkipass/trips)+ "\t"+floattoeuro(avgSkihire/trips) +"\t" + floattoeuro(avgTotals/trips) + "\n" );
			display("\nEnd of " + filename + "\n");
		} catch (IOException x) {
    			System.err.format("IOException: %s%n", x);
		}
	} /* end of readFile */


     
        /* float to euro convert float to string currency formatted for displaying on screen*/
        public static String floattoeuro(double avgCost2){
        	
        		String str = String.format("%.02f", avgCost2);
        		return str;
        
        } /* end of floattoeuro */



	private static void addTrip(String filename) {
		location = getDescription("Where is the trip to");
		flights = getCost("Enter flights Cost");
		connection = getCost("Enter Connection Cost");
		accommodation = getCost("Enter Accommodation Cost");
		skipass = getCost("Enter Ski Pass Cost");
		skihire = getCost("Enter Ski Hire Cost");
		lessons = getCost("Enter Ski Lessons Cost");
		insurance = getCost("Enter Insurance Cost");
		days = (int) getCost("How many days are you there for "); //cast double to an int
		dailyspend = getCost("Enter your average daily spend ");
 		weeklyspend = days * dailyspend;
		// total costs and print
		total = flights + connection + accommodation + skihire + skipass + lessons + insurance +weeklyspend;
		display("The Total Cost is ");
		displayf(total);
	
		SkiTripInfo tripInfo = new SkiTripInfo(location, flights, connection, accommodation, skihire, skipass, lessons, insurance, days, dailyspend, weeklyspend, total);

		writeToFile(location +","
			 +flights+","
			 +connection+","
			 +accommodation+","
			 +skihire+","
			 +skipass+","
			 +lessons+","
			 +insurance+","
			 +days+","
			 +dailyspend+","
			 +weeklyspend+","
			 + total +",\n", outputFile);
		display("\n *** End of Add Trip *** \n");
		display(tripInfo.toString());
	} /* end of addTrip */


	/* Main Function */
	public static void main (String[] args) {
		int var = 0;
		String input;
		Scanner scanner = new Scanner (System.in );
		do {
		display("Ski Calculator");
		display("\n");
		display("1. Add Trip\n");
		display("2. View Trips\n");
		display("3. Calculate Averages for Trips\n");
		display("0. Exit\n");
		display("Select ?");
		input = scanner.next();
		var = Integer.parseInt(input);

		switch(var) {
		case 1:
			addTrip(outputFile);
			break;
		case 2:
			readFile(outputFile);
			break;
		case 3:
			readandcalcFile(outputFile);
			break;
		case 0:
			display("go again\n");
			break;
		default:
			display("default ");
			break;
		} /* end of switch */
		}while(var ==1|| var==2);

		display("\n *** The End *** \n");
	} /* end of main */

} /* end of prog */


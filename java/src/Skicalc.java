/* Skicalc.java 
 * calculate the costs for a skiholiday 
 */

package conors;

import java.util.Scanner;
import java.io.*;
//import java.util.String;

public final class Skicalc {
	

	public static int days =0;
	public static double total  = 0;
	public static double flights  = 200;
	public static double connection  = 50;
	public static double accommodation  = 500;
	public static double skipass  = 100;
	public static double skihire  = 120;
	public static String outputFile  = "skidata.txt";
 
	private static void display(String text ) {
		System.out.println(text);
	}

	private static void displayf(double num) {
		System.out.format("%.2f", num);
	}

	private static double getCost(String quest)
	{
		Scanner input = new Scanner(System.in);
		display(quest);
		String sInput = input.next();;
		return  Double.parseDouble(sInput);
		
	}	

/* write a string to a file, if file exists append string to file. */
	private static void writeToFile(String str, String filename) {

      		try {
            		File dataFile = new File(filename);
			PrintWriter out =null;
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
            		File dataFile = new File(filename);
			FileReader fileReader = new FileReader(dataFile);
			BufferedReader reader = new BufferedReader(fileReader);
			//BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    			String line = null;
			display("Contents of " + filename + "\n");
    			while ((line = reader.readLine()) != null) {
        			display(line);
    			}
			display("\nEnd of " + filename + "\n");
		} catch (IOException x) {
    			System.err.format("IOException: %s%n", x);
		}
	} /* end of readFile */

	public static void main (String[] args) {

		display("Ski Calculator");
		String location = "Austria";
		flights = getCost("Enter flights Cost");
		connection = getCost("Enter Connection Cost");
		accommodation = getCost("Enter Accommodation Cost");
		skipass = getCost("Enter Ski Pass Cost");
		skihire = getCost("Enter Ski Hire Cost");
 		
		// total costs and print
		total = flights + connection + accommodation + skihire + skipass;
		display("The Total Cost is ");
		displayf(total);
		writeToFile(location +","
			 +flights+","
			 +connection+","
			 +accommodation+","
			 +skihire+","
			 +skipass+","
			 + total +",\n", outputFile);
		display("\n *** The End *** \n");
		readFile(outputFile);
	} /* end of main */

} /* end of prog */


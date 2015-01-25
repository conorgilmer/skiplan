/* Skiplan.java 
 * calculate the costs for a skiholiday 
 */

package conors;

import java.util.Scanner;
//import java.util.String;

public final class Skicalc {
	

	public static int days =0;
	public static double total  = 0;
	public static double flights  = 200;
	public static double connection  = 50;
	public static double accommodation  = 500;
	public static double skipass  = 100;
	public static double skihire  = 120;
 
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

	public static void main (String[] args) {

		display("Ski Calculator");

		flights = getCost("Enter flights Cost");
		connection = getCost("Enter Connection Cost");
		accommodation = getCost("Enter Accommodation Cost");
		skipass = getCost("Enter Ski Pass Cost");
		skihire = getCost("Enter Ski Hire Cost");
 		
		// total costs and print
		total = flights + connection + accommodation + skihire + skipass;
		display("The Total Cost is ");
		displayf(total);
		display("\n *** The End *** \n");

	} /* end of main */

} /* end of prog */


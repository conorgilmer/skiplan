public class SkiTripInfo
{
	SkiTripInfo(		String location, 
				double flights, 
				double connection, 
				double accommodation, 
				double skihire, 
				double skipass, 
				double lessons, 
				double insurance, 
				int    days, 
				double dailyspend, 
				double weeklyspend, 
				double total)
	{
		this.location      = location;
		this.flights       = flights;
		this.connection    = connection;
		this.accommodation = accommodation;
		this.skihire       = skihire;
		this.skipass       = skipass;
		this.lessons       = lessons;
		this.insurance     = insurance;
		this.days          = days;
		this.dailyspend    = dailyspend;
		this.weeklyspend   = weeklyspend;
		this.total         = total;
	}

	/* fields in the object */
	String location; 
	double flights; 
	double connection; 
	double accommodation; 
	double skihire; 
	double skipass; 
	double lessons; 
	double insurance; 
	int    days; 
	double dailyspend; 
	double weeklyspend; 
	double total;
	
	/* return the values with in each item in the object */
	public String getLocation()     { return location;}
	public double getFlights()      { return flights;} 
	public double getConnection()   { return connection;} 
	public double getAccommodation(){ return accommodation;} 
	public double getSkihire()      { return skihire;} 
	public double getSkipass()      { return skipass;} 
	public double getLessons()      { return lessons;} 
	public double getInsurance()    { return insurance;} 
	public int    getDays()         { return days;} 
	public double getDailyspend()   { return dailyspend;} 
	public double getWeeklyspend()  { return weeklyspend;} 
	public double getTotal()        { return total;}

	/* return a string for a csv file record */
	public String csvRecord() {
		return location + ","+ flights +","+ connection+","+ accommodation+","+ skipass+","+skihire+", 0, 0, 0, 0, 0,"+ total +"\n";
	}
	
	/* print out the contents of the object*/
	public String toString()
	{
		return "Ski Trip Info " + "\n" +
		"Location      = " + location + "\n" +
		"flights       = " + flights + "\n" +
		"connecion     = " + connection + "\n" +
		"Accommodation = " + accommodation + "\n" +
		"Ski Hire      = " + skihire + "\n" +
		"Skipass       = " + skipass + "\n" +
		"Lessons       = " + lessons + "\n" +
		"Insurance     = " + insurance + "\n" +
		"Days          = " + days + "\n" +
		"Daily spend   = " + dailyspend + "\n" +
		"Holiday spend = " + weeklyspend + "\n" +
		"Total         = " + total + "\n"; 
	}
}


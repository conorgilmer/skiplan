
public class SkiHireDetails
{
	SkiHireDetails(		String description, 
				double skis, 
				double boots, 
				double helmet, 
				double total)
	{
		this.description   = description;
		this.skis          = skis;
		this.boots         = boots;
		this.helmet        = helmet;
		this.total         = total;
	}

	/* fields in the object */
	String description; 
	double skis; 
	double boots; 
	double helmet; 
	double total;
	
	/* return the values with in each item in the object */
	public String getDescription()  { return description;}
	public double getSkis()         { return skis;} 
	public double getBoots()        { return boots;} 
	public double getHelmet()       { return helmet;} 
	public double getTotal()        { return total;}

	
	/* print out the contents of the object*/
	public String toString()
	{
		return "Ski Pacl Info " + "\n" +
		"Desciption    = " + description + "\n" +
		"Skis          = " + skis   + "\n" +
		"Boots         = " + boots  + "\n" +
		"Helmet        = " + helmet + "\n" +
		"Total         = " + total  + "\n"; 
	}
}


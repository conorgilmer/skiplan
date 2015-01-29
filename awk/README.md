#Using Awk to tabulate a table/spreadsheet

Calculate the average of each element in the spreadsheet

##Print to Screen
*usage* 
awk -f trip.awk costs.csv 
or cat costs.csv | awk -f trip.awk

##Generate HTML

*Usage*
awk -f tripshtml.awk costs.csv > output.html

##Table Layout
[Location	Flights	Trans	Accom	Skipass	SkiPack]

#Using Awk to tabulate a table/spreadsheet

Calculate the average of each element in the spreadsheet

##Print to Screen
*usage* 
awk -f trip.awk costs.csv > report.out 
or cat costs.csv | awk -f trip.awk

##Generate HTML

*Usage*
awk -f tripshtml.awk costs.csv > output.html

##Generate with Average Min and Maximum Column value

*Usage*
awk -f tripsamm.awk costs.csv > reportavgminmax.out

##CSV File Layout
The input file is a csv file 
[Location, Flights, Transfers, Accommodation, Skipass, SkiPack]

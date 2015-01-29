#!/bin/awk
# Usage : awk -f tripshtml.awk costs.csv > table.html
# or   cat costs.csv | awk -f tripshtml.awk >table.html
# calculate the average spend for each item in the csv file
# 
#
BEGIN {	FS = ","
	Title="Ski Trips Average and Totals"
	print "<html>\n<head>\n<title>"Title"</title></head><body><h1>"Title"</h1><table border=0><tr><th>No.</tn><th>Location</th><th>Flights</th><th>Trans</th><th>Accom</th><th>Skipass</th><th>SkiPack</th><th>Total</th></tr>\n";
}
{	sum = $2 + $3 + $4 + $5 + $6;
	printf "<tr><td>%s</td><td>%s</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td></tr>\n" , NR, $1, $2, $3, $4, $5, $6, sum;
	sum2+=$2; 
	sum3+=$3;
	sum4+=$4;
	sum5+=$5;
	sum6+=$6;
	sumt+=sum; 
	++n
} 
END { 
	printf "<tr><td> </td><td><b>Average</b></td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td></tr>\n", sum2/n, sum3/n, sum4/n, sum5/n, sum6/n, sumt/n 
	print "</table>\n</body>\n</html>";
}

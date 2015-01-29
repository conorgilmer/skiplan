#!/bin/awk
# Usage : awk -f trip.awk costs.csv
# or   cat costs.csv | awk -f trip.awk
# calculate the average spend for each item in the csv file
# 
#
BEGIN {	FS = ","
	print "\nNo.\tLocation\tFlights\tTrans\tAccom\tSkipass\tSkiPack\tTotal\n";
}
{	sum = $2 + $3 + $4 + $5 + $6;
	printf "%s\t%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n" , NR, $1, $2, $3, $4, $5, $6, sum;
	sum2+=$2; 
	sum3+=$3;
	sum4+=$4;
	sum5+=$5;
	sum6+=$6;
	sumt+=sum; 
	++n
} 
END { 
	printf "\n\tAverage:\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n\n", sum2/n, sum3/n, sum4/n, sum5/n, sum6/n, sumt/n 
}

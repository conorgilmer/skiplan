#!/bin/awk
# Usage : awk -f trip.awk costs.csv > report.out
# or   cat costs.csv | awk -f trip.awk
# calculate the average spend for each item in the csv file
# 
#
BEGIN {	FS = ","
	print "\nSki Holiday Costs Report"
	print "\nNo.\tLocation\tFlights\tTrans\tAccom\tSkipass\tSkiPack\tTotal\n";
min2 =1000
min3 =1000
min4 =1000
min5 =1000
min6 =1000
mint =1000
}
{	sum = $2 + $3 + $4 + $5 + $6;
	printf "%s\t%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n" , NR, $1, $2, $3, $4, $5, $6, sum;
	sum2+=$2; 
	sum3+=$3;
	sum4+=$4;
	sum5+=$5;
	sum6+=$6;
	sumt+=sum; 
	++n;
	
	min2 = (min2>$2)?$2:min2
	min3 = (min3>$3)?$3:min3
	min4 = (min4>$4)?$4:min4
	min5 = (min5>$5)?$5:min5
	min6 = (min6>$6)?$6:min6
	mint = (mint>sum)?sum:mint
	
	max2=(max2>$2)?max2:$2
	max3=(max3>$3)?max3:$3
	max4=(max4>$4)?max4:$4
	max5=(max5>$5)?max5:$5
	max6=(max6>$6)?max6:$6
	maxt=(maxt>sum)?maxt:sum
} 
END { 
	printf "\n\tAverage:\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n\n", sum2/n, sum3/n, sum4/n, sum5/n, sum6/n, sumt/n 

	printf "\tMinimum: \t %.2f\t %.2f\t %.2f\t %.2f\t %.2f\t%.2f\n", min2, min3,min4,min5,min6,mint;
	printf "\tMaximum: \t %.2f\t %.2f\t %.2f\t %.2f\t %.2f\t%.2f\n", max2, max3,max4,max5,max6,maxt;
}

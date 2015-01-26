#!usr/bin/perl
print "\n *** Ski Calculator *** \n\n";

print " Cost of Flights = ";
$flights = <>;

print " Cost of Connection/transfer = ";
$transfer = <>;

print " Cost of Accommodation = ";
$accom = <>;

print " Cost of Skihire = ";
$skihire = <>;

print " Cost of Skipass = ";
$skipass =<>;

$total = $flights + $transfer + $accom + $skihire + $skipass;

print "\nThe total cost is €", $total , "\n";

print "\n *** The End *** \n";

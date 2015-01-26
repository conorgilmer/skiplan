#!usr/bin/perl

sub quest {
my $questtext = shift;
my $answer = 0;
print " Cost of ", $questtext, "? ";
$answer = <>;


}
$flights = 0;
$accom = 0;
$transfer = 0;
$skihire = 0;
$skipass = 0;
$insurance = 0;
$lessons = 0;

print "\n *** Ski Calculator *** \n\n";
$flights = quest("Flights");
$transfer = quest("Airport Transfer");
$accom = quest("Accommodation");;
$skihire = quest("Ski Hire");;
$skipass = quest("Ski pass");;
$insurance = quest("Ski insurance");;
$lessons = quest("Ski lessons");;

$total = $flights + $transfer + $accom + $skihire + $skipass + $insurance + $lessons;

print "\nThe total cost is €", $total , "\n";

print "\n *** The End *** \n";

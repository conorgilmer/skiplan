#!usr/bin/perl
# ------------------------------------- #
# skicalc.pl                            #
# Calculate the costs for a ski trip    #
# Conor Gilmer (conor.gilmer@gmail.com) #
# ------------------------------------- #


# subroutines / functions               #

# ask a question and                    #
# get the user input for that question  #
sub quest {
    my $questtext = shift;
    my $answer = 0;
    print " Cost of ", $questtext, "? ";
    $answer = <>;
}

# write the details submmited to a file #
sub writeRecord {
    my $text     = shift;
    my $f        = shift;
    my $t        = shift;
    my $a        = shift;
    my $sp       = shift;
    my $sh       = shift;
    my $i        = shift;
    my $l        = shift;
    my $cost     = shift;
    my $filename = 'skihols.txt';

    open (my $fh, '>>', $filename) or die "Could not open '$filename'";
    print $fh $text.",".$f.",".$t.",".$a.",".$sp.",".$sh.",".$i.",".$l.",".$cost.",\n";
    close $fh;
    print "record written to $filename \n";
}


# the main function starts here         #

# initialise variables                  #
$flights   = 0;
$accom     = 0;
$transfer  = 0;
$skihire   = 0;
$skipass   = 0;
$insurance = 0;
$lessons   = 0;
$total     = 0;

print "\n *** Ski Calculator *** \n\n";
$flights   = quest("Flights");
chomp($flights);
$transfer  = quest("Airport Transfer");
chomp($transfer);
$accom     = quest("Accommodation");;
chomp($accom);
$skipass   = quest("Ski Pass");;
chomp($skipass);
$skihire   = quest("Ski Hire");;
chomp($skihire);
$insurance = quest("Ski Insurance");;
chomp($insurance);
$lessons   = quest("Ski Lessons");;
chomp($lessons);

$total = $flights + $transfer + $accom + $skihire + $skipass + $insurance + $lessons;

print "\nThe total cost is €", $total , "\n";

# do you wish to write this to a file   #
print "\n Do you want to write this to a file (y or n)? ";
$saveit = <>;
chomp($saveit);
if( $saveit eq "y") {
    print "Enter a name for the holiday ?";
    $name = <>;
    chomp($name);
    writeRecord($name, $flights, $transfer, $accom, $skipass, $skihire, $insurance, $lessons, $total);
} else {
    print "\nRecord not printed\n";
}

print "\n *** The End *** \n";

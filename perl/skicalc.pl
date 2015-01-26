#!usr/bin/perl
# ------------------------------------- #
#          skicalc.pl                   #
#                                       #
# Calculate the costs for a ski trip    #
# you can write it to a csv file and    #
# see previous trips you saved          #   
#                                       #
# Conor Gilmer (conor.gilmer@gmail.com) #
# ------------------------------------- #

# ------ subroutines / functions ------ #

# ------ ask a question and ----------- #
# ------ get the input for a question - #
sub quest {
    my $questtext = shift;
    my $answer = 0;
    print " Cost of ", $questtext, "? ";
    $answer = <>;
} # end of quest

# ------ write the details to a file -- #
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
} # end of writeRecord

# ------ read the ski holidays file ---- #
sub readFile {
#my $filename = "skihols.txt";
my $filename = shift;

open(FILE,"<$filename") or die "Can't open filelist\n";
print "\n\n *** Contents of $filename ***\n\n";
        while (<FILE>) {
                chomp;
                print $_;
                print "\n";
                }
        close(FILE);
print "\n *****************************\n";
} # end of readFile

# ------ show the list of holidays ? -- #
sub showRecords { 
# do you wish to write this to a file   #
    print "\n Do you want to see the records of holidays (y or n)? ";
    my $showrecords = <>;
    chomp($showrecords);
    if( $showrecords eq "y") {
        readFile("skihols.txt");
    } else {
        print "\nFile not printed\n";
    }
} # end of showRecords

# -------- add a ski holiday ---------- #
sub addSkiHoliday {
# initialise variables                  #
    my $flights   = 0;
    my $accom     = 0;
    my $transfer  = 0;
    my $skihire   = 0;
    my $skipass   = 0;
    my $insurance = 0;
    my $lessons   = 0;
    my $total     = 0;

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
    my $saveit = <>;
    chomp($saveit);
    if( $saveit eq "y") {
        print "Enter a name for the holiday ?";
        my $name = <>;
        chomp($name);
        writeRecord($name, $flights, $transfer, $accom, $skipass, $skihire, $insurance, $lessons, $total);
    } else {
        print "\nRecord not printed\n";
    }
} # end of addSkiHoliday



$choice =9;
# ------ main function ---------------- #
while ($choice ne "0") {
    print "\n *****************************";
    print "\n *                           *";
    print "\n * Ski Holiday Costs         *";
    print "\n *                           *";
    print "\n * 1 - Add a Holiday         *";
    print "\n * 2 - List Holidays         *";
    print "\n * 0 - Exit                  *";
    print "\n *                           *";
    print "\n *****************************";

    $choice =<>;
    chomp($choice);
    if( $choice eq "2"){
        showRecords();
    } elsif( $choice eq "1"){
        addSkiHoliday();
    } else {
        print "\n *****************************";
        print "\n *         The End           *";
        print "\n *****************************\n\n";
    }
} # end of while

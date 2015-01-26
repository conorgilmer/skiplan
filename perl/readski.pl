#!/usr/bin/perl
use strict;
use warnings;


my $filename = "skihols.txt";


open(FILE,"<$filename") or die "Can't open filelist\n";
print "Contents of $filename \n";
	# -----------------------------------------------
	# First suck the filelist into an array, and join
	# the continuation lines to the filename line
	# -----------------------------------------------
	while (<FILE>) {
		chomp;
		print $_;
		print "\n";
		}
	close(FILE);

#!/usr/bin/perl
use strict;
use warnings;

use Path::Class;
use autodie; # die if problem reading or writing a file

my $filename = "skihols.txt";

#my $dir = dir("/data"); # / could be /data
#my $file = $dir->file($filename);

my $file = file($filename);

# Read in the entire contents of a file
my $content = $file->slurp();

# openr() returns an IO::File object to read from
my $file_handle = $file->openr();

# Read in line at a time
while( my $line = $file_handle->getline() ) {
    print $line;
}

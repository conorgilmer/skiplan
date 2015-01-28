#!/usr/bin/python

import csv

dataFile = open('skicosts.csv', "rb")
reader = csv.reader(dataFile)

rownum = 0
for row in reader:
  if rownum == 0:
    header = row
  else:
    colnum = 0
    for col in row:
      print '%-8s: %s' % (header[colnum], col)
      colnum += 1
  rownum += 1

dataFile.close()

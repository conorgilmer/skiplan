#!/usr/bin/python

from pylab import *

# make a square figure and axes
figure(1, figsize=(6,6))
ax = axes([0.1, 0.1, 0.8, 0.8])

# The slices will be ordered and plotted counter-clockwise.
colours = ['yellow', 'orange', 'red', 'blue', 'green', 'purple']
labels = 'Flights', 'Transfers', 'Accommodation', 'Skipass', 'Skipack', 'insurance'
fracs = [200, 50, 300, 100, 110, 26]
explode=(0, 0.05, 0, 0, 0,0)

pie(fracs, explode=explode, labels=labels,
                autopct='%1.1f%%', shadow=True, startangle=90, colors=colours)
                # The default startangle is 0, which would start
                # the Frogs slice on the x-axis.  With startangle=90,
                # everything is rotated counter-clockwise by 90 degrees,
                # so the plotting starts on the positive y-axis.

title('Breakdown of Ski Trip Costs', bbox={'facecolor':'0.8', 'pad':5})

show()

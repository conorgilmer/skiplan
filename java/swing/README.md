#SkiCalcSwing.java
Pretty basic app
Java Swing version of adding up ski holiday costs and listing trips stored on a csv file

>ant clean compile jar run

or

>ant all

##To do

+ analysis of compared to previous trips
+ generate breadown chart
+ generate pie chart
+ add PDF Generator for previous trips(merge with report)

##Done

+ Read in default values from Resource Bundle
+ Set up Apache Ant build - with resource bundle bundled in build
+ write to file
+ read from file
+ input validation
+ Generate Report and Write to Report file
+ Added Simple Bar Chart of Costs for each trip
+ Added Simple line Chart of Costs for each trip
+ Added Generate a PDF Report (using PDFBox pdfbox.1.8.8.jar, apache-commons.jar and fontbox-1.5.0.jar))

/*
 * skicalc.c
 * calculate the costs of a ski holiday
 */
#include<stdio.h>
#include "skicalc.h"
#include <stdlib.h>
//#include <string.h>
//#include <assert.h>


void addRecord(void);
char mainManu(void);
void readRecords(void);
void writeRecord(char[], float, float, float, float,float, float, float, int, float,float,float);


void readRecords(void)
{
	int   records     = 0;
	float tFlights    = 0;
	float tTransfers  = 0;
	float tAccom      = 0;
	float tPass       = 0;
	float tHire       = 0;
	float tLessons    = 0;
	float tInsurance  = 0;
	float tCost       = 0;
	
	char** colitems;
        FILE *ptr_file;
        char buf[1000];

        ptr_file =fopen("skidata.csv","r");
        if (!ptr_file)
		printf("no file????\n");
	else {
                printf("\nResort\tFlights\tConn\tAccomm\tSkipass\tSkihire\tTotal\n\n");
        while (fgets(buf,1000, ptr_file)!=NULL)
        {	
		records++;
                colitems = str_split(buf, ',');
                printf("%s\t%s\t%s\t%s\t%s\t%s\t%s", colitems[0],colitems[1],colitems[2],colitems[3],colitems[4],colitems[5],colitems[11]);

		tFlights   = tFlights   + atof(colitems[1]);
		tTransfers = tTransfers + atof(colitems[2]);
		tAccom     = tAccom     + atof(colitems[3]);
		tPass      = tPass      + atof(colitems[4]);
		tHire      = tHire      + atof(colitems[5]);
		tCost      = tCost      + atof(colitems[11]);
        }
        fclose(ptr_file);

        printf("\n\nAvg.\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n\n", tFlights/records, tTransfers/records, tAccom/records, tPass/records, tHire/records, tCost/records);
	}
}


void addRecord(void)
{
    // Local Declarations
    float total, flights, connection, accommodation, skipass, skihire;
    float skilessons, skiinsurance, dailyspend, weekspend;

    // initialise values
    int days        = 6;
    char *location  = " ";
    total           = 0;
    connection      = 0;
    accommodation   = 0;
    skipass         = 0;
    skihire         = 0;
    skilessons      = 0;
    skiinsurance    = 0;
    dailyspend      = 0;
    weekspend       = 0;

    printf("\n");
    printf("*************************************\n");
    printf("Calculate the costs of a ski holiday.\n");
    printf("*************************************\n");
    printf("\n");

    // get input for costs
    location = getInputStr("Where are you going?");
    printf("location = %s \n", location);
    flights = getInput("Flights");
    connection = getInput("Connection"); 
    accommodation = getInput("accommodation");
    skipass = getInput("ski pass");
    skihire = getInput("ski hire");
    skilessons = getInput("ski lessons");
    skiinsurance = getInput("ski insurance");
    dailyspend = getInput("daily spend");
    days =  getInputInt("How many days");

    weekspend = dailyspend * days;
    total = flights + connection + accommodation + skipass + skihire + skilessons + skiinsurance + weekspend ;

    // print out results
    printf("\n*************************************\n");
    printf("Destination:         %s \n\a", location);
    printf("Flights cost:        %5.2f Euro\n\a", flights);
    printf("Connection cost:     %5.2f Euro\n\a", connection);
    printf("Accommodation cost:  %5.2f Euro\n\a", accommodation);
    printf("Skipass cost:        %5.2f Euro\n\a", skipass);
    printf("Skishire cost:       %5.2f Euro\n\a", skihire);
    printf("Ski lessons cost:    %5.2f Euro\n\a", skilessons);
    printf("Ski insurance cost:  %5.2f Euro\n\a", skiinsurance);
    printf("Daily spend %d days: %5.2f Euro\n\a", days, dailyspend);
    printf("                     ------\n\a");
    printf("The total cost is    %5.2f Euro\a\n", total);
    printf("                     ======\n\a");
    printf("\n*************************************\n");

    writeRecord(location, flights, connection,accommodation,skipass,skihire,skilessons,skiinsurance, days, dailyspend, weekspend, total);

	free(location);
} /* end of addRecord */


void writeRecord(char place[10], float flight, float con, float acc, float pass, float hire, float lessons, float insurance, int days, float daily, float weekly, float tot)
{
   FILE *fp;

   fp = fopen("skidata.csv", "a+");
   fprintf(fp, "%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%.2f,%.2f,%.2f\n", place, flight,con, acc,pass,hire, lessons, insurance, days, daily, weekly, tot);
  // fputs("This is testing for fputs...\n", fp);
   fclose(fp);

   printf("*** Record Saved to file ***\n");
} /* end of writeRecord */


char mainMenu(void) {
    char ch ='c';
    printf("*************************************\n");
    printf("*                                   *\n");
    printf("*       Ski Trip Calculator         *\n");
    printf("*  -------------------------------- *\n");
    printf("*  1) Add Trip                      *\n");
    printf("*  2) View Trips                    *\n");
    printf("*  3) Generate Report               *\n");
    printf("*  0) Exit                          *\n");
    printf("*  -------------------------------- *\n");
    printf("*                                   *\n");
    printf("*************************************\n");
    scanf("%c", &ch);

    return ch;
} /* end of mainMenu */


int main(void) {
	char choice = 'r';
	do {	
	choice = mainMenu();
	if (choice == '1') {
        addRecord();}
	else if (choice =='2') {
		printf("*** View Trips ***\n");
		readRecords();
	}
	} while ( choice != '0');


	return 0;
} /* end of main */

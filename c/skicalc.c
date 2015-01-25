/*
 * skicalc.c
 * calculate the costs of a ski holiday
 */
#include<stdio.h>
#include "skicalc.h"

int main(void)
{
    // Local Declarations
    float total, flights, connection, accommodation, skipass, skihire;
    float skilessons, skiinsurance, dailyspend, weekspend;

    // initialise values
    int days      = 6;
    total         = 0;
    connection    = 0;
    accommodation = 0;
    skipass       = 0;
    skihire       = 0;
    skilessons    = 0;
    skiinsurance  = 0;
    dailyspend    = 0;
    weekspend     = 0;

    printf("\n");
    printf("*************************************\n");
    printf("Calculate the costs of a ski holiday.\n");
    printf("*************************************\n");
    printf("\n");

    // get input for costs
    flights = getInput("Flights");
    connection = getInput("Connection");
    accommodation = getInput("accommodation");
    skipass = getInput("ski pass");
    skihire = getInput("ski hire");
    skilessons = getInput("ski lessons");
    skiinsurance = getInput("ski insurance");
    dailyspend = getInput("daily spend");
    weekspend = dailyspend * days;
    total = flights + connection + accommodation + skipass + skihire + skilessons + skiinsurance + weekspend ;

    // print out results
    printf("\n");
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

    return 0;

}

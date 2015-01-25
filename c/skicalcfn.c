#include<stdio.h>


float getInput( char *word) 
{
	float input =0;
    	printf("Enter the cost of %s: ", word);
    	scanf("%f", &input);
	return input;

}


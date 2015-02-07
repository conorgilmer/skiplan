#include<stdio.h>
#include<stdlib.h>
#include <string.h>
#include <assert.h>

float getInput( char *word) 
{
	float input =0;
    	printf("Enter the cost of %s: ", word);
    	scanf("%f", &input);
	return input;
} /* end of getInput */


int getInputInt( char *word) 
{
	int input = 0;
    	printf("How many %s: ", word);
    	scanf("%d", &input);
	return input;
} /* end of getInputInt */

/* getInputStr returns a string you need to free this string afte free(str); */
char* getInputStr( char *word){
    	printf("%s: ", word);
    char *s = (char*) malloc( 100 );
    scanf("%s",s);
    return s;
} /* end of getInputStr */

/* split string  function */
char** str_split(char* a_str, const char a_delim)
{
    char** result    = 0;
    size_t count     = 0;
    char* tmp        = a_str;
    char* last_comma = 0;
    char delim[2];
    delim[0]         = a_delim;
    delim[1]         = 0;

    /* Count how many elements will be extracted. */
    while (*tmp)
    {
        if (a_delim == *tmp)
        {
            count++;
            last_comma = tmp;
        }
        tmp++;
    }

    /* Add space for trailing token. */
    count += last_comma < (a_str + strlen(a_str) - 1);

    /* Add space for terminating null string so caller
       knows where the list of returned strings ends. */
    count++;

    result = malloc(sizeof(char*) * count);

    if (result)
    {
        size_t idx  = 0;
        char* token = strtok(a_str, delim);

        while (token)
        {
            assert(idx < count);
            *(result + idx++) = strdup(token);
            token = strtok(0, delim);
        }
        assert(idx == count - 1);
        *(result + idx) = 0;
    }

    return result;
} /* end of str_split */



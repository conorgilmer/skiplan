/* skiplan get input h file */

float   getInput( char *word); 
int     getInputInt( char *word); 
char *  getInputStr ( char *word ); 
char ** str_split(char* a_str, const char a_delim);

typedef struct ski_trip_details {
	char *location;
	float total;
	float flights;
	float connection;
	float accommodation;
	float skipass;
	float skihire;
	float skilessons;
	float skiinsurance;
	float dailyspend;
	int days;;
} ski_trip_struct;

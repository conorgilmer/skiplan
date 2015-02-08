/* skiplan get input h file */

float   getInput( char *word); 
int     getInputInt( char *word); 
char *  getInputStr ( char *word ); 
char ** str_split(char* a_str, const char a_delim);

/* structure for SkiTrip */
typedef struct SkiTrips {
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
	int   days;
} SkiTrip;


/* structure for Skipack */
typedef struct SkiPacks {
	char  type[10];
	float skis;
	float boots;
	float helmet;
} SkiPack;

/* structure for Report */
typedef struct Reports {
	float avg_flights;
	float avg_transfers;
	float avg_accom;
	float avg_pass;
	float avg_hire;
	float avg_cost;

} Report;



/*
 * Skicosts.cpp
 * Basic calculate the costs of a ski trip
 *  Created on: Feb 8, 2015
 *      Author: conor gilmer
 */

#include <iostream>
#include <string>
#include <fstream>

using namespace std;

const char *FILENAME = "data.csv";

int main() {

	string location  = " ";
	double flights   =  0;
	double transfers =  0;
	double accom     =  0;
	double skipass   =  0;
	double skihire   =  0;
	double total     =  0;

	// get inputs
	cout << "Ski Costs Calculator\n";
	cout << "Where are you going? ";
	getline(cin, location);
	cout << "How much are the flights?";
	cin >> flights;
	cout << "How much are airport transfers?";
	cin >> transfers;
	cout << "How much is Accommodation?";
	cin >> accom;
	cout << "How much does a SkiPass Cost?";
	cin >> skipass;
	cout << "How much does Ski Hire Cost?";
	cin >> skihire;

	// calculate total
	total = flights + transfers + accom + skipass + skihire;

	// output total
	cout << "Total Cost  for your trip to " << location << " will be  " << total << " euro.\n";

	// save ski trip to file
	char dl =',';
    ofstream fout(FILENAME, ios::app);
    fout << location <<dl<<flights<<dl<<transfers<<dl<<accom<<dl<<skipass<<dl<<skihire<<endl;
    //close file
    fout.close();
    cout << "Skitrip written to \"" << FILENAME << "\"";
    cout << endl;

    // display stored skitrips
    cout << "Ski Trips" <<endl;
    ifstream fin(FILENAME);
    char ch;
    while (fin.get(ch))
        cout << ch;
    fin.close();

    cout << "End of Ski Trips" << endl;

	return 0;
}



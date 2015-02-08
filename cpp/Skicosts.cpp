/*
 * Skicosts.cpp
 * Basic calculate the costs of a ski trip
 *  Created on: Feb 8, 2015
 *      Author: conor gilmer
 */

#include <iostream>
#include <string>

using namespace std;

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
	cout << "Total Cost will be  " << total << " euro.\n";
	return 0;
}



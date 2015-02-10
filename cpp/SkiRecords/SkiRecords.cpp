/* Ski Trips recording system 
 * add a ski trip
 * show skitrips records
 * search record of skitrips
 * update skitrip record
 * delete skitrip record
 *  
 * calculate the average, minimum and maximum costs
 * records stored in a binary file games.rec
 * 
 * compiled using make calling g++  
 * 
 */

#include<iostream>
#include<fstream>
#include<string>
#include<cstdlib>
using std::cout;
using std::cin;
using std::endl;
using std::fstream;
using std::ofstream;
using std::ifstream;
using std::ios;
using std::string;

/* filename */
const string filename = "skitrip.rec";

class skitrip_record
{
    private:
	char   location[12];
	double flights;
	double transfers;
	double accommodation;
	double skipass;
	double skihire;
	double total;
    public:
        void read_data();
        void generate_report();
        void show_data(int);
        void write_record();
        void read_record();
        void search_record();
        void edit_record();
        void delete_record();
};

/* generate report and calculate averages and totals */ 
void skitrip_record::generate_report()
{   int    trips            = 0;
    double t_flights        = 0;
    double t_transfers      = 0;
    double t_accommodation  = 0;
    double t_skipass        = 0;
    double t_skihire        = 0;
    double t_costs          = 0;

    ifstream infile;
    infile.open(filename, ios::binary);
    if(!infile)
    {
        cout<<"Error in Opening! "<< filename <<" File Not Found!!"<<endl;
        return;
    }
    cout<<"\n*** \t \t Report Data from file \t \t ***"<<endl;
    cout<<"\nLocation \tFlights\tTrans\tAccom\tPass\tHire\tTotal"<<endl;
    while(!infile.eof())
    {
        if(infile.read(reinterpret_cast<char*>(this), sizeof(*this))>0)
        { trips++;
	cout<<location<<"\t"<<flights<<"\t"<<transfers<<"\t"<<accommodation<<"\t"<<skipass<<"\t"<<skihire<<"\t"<<total<<endl;
	t_flights       = t_flights + flights;
	t_transfers     = t_transfers + transfers;
	t_accommodation = t_accommodation + accommodation;
	t_skipass       = t_skipass + skipass;
	t_skihire       = t_skihire + skihire;
	t_costs         = t_costs + total;
	/* calculate rating change */
        } /* end of if */
    } /* end of while loop */
    cout<<"\nAverages\t"<<t_flights/trips<<"\t"<<t_transfers/trips<<"\t"<<t_accommodation/trips<<"\t"<<t_skipass/trips<<"\t"<<t_skihire/trips<<"\t"<<t_costs/trips<<endl;
    cout<<"\n*** \t \t End of Report    \t \t ***\n"<<endl;
    infile.close();
} /* end of skitrip_record::list */

void skitrip_record::read_data()
{
    cout<<"\nLocation: ";
    cin>>location;
    cout<<"Flights: ";
    cin>>flights;
    cout<<"Transfers: ";
    cin>>transfers;
    cout<<"Accommodation ";
    cin>>accommodation;
    cout<<"Ski Pass: ";
    cin>>skipass;
    cout<<"Ski Hire: ";
    cin>>skihire;
    cout<<endl;
    total = flights + transfers + accommodation + skipass + skihire;
} /* end of skitrip_record::read_data */

void skitrip_record::show_data(int game)
{
    cout<<"Location:\t\t"<<location<<endl;
    cout<<"Flights:\t"<<flights<<endl;
    cout<<"Transfers: \t\t"<<transfers<<endl;
    cout<<"Accommodation:\t"<<accommodation<<endl;
    cout<<"Skipass:\t\t"<<skipass<<endl;
    cout<<"Skihire:\t\t"<<skihire<<endl;
    cout<<"Total Cost:\t"<<total<<endl;

    cout<<"-------------------------------------------"<<endl;
} /* end of show_data */

void skitrip_record::write_record()
{
    ofstream outfile;
    outfile.open(filename, ios::binary|ios::app);
    read_data();
    outfile.write(reinterpret_cast<char *>(this), sizeof(*this));
    outfile.close();
} /* end of write_record */

/* Read the records one by one and list them on the screen*/
void skitrip_record::read_record()
{   int trip = 0;
    ifstream infile;
    infile.open(filename, ios::binary);
    if(!infile)
    {
        cout<<"Error in Opening! "<< filename <<" File Not Found!!"<<endl;
        return;
    }
    cout<<"\n*******************************************"<<endl;
    cout<<"*** Records Listed on the File System   ***"<<endl;
    cout<<"*******************************************"<<endl;
    cout<<"-------------------------------------------"<<endl;
    while(!infile.eof())
    {
	trip++;
        if(infile.read(reinterpret_cast<char*>(this), sizeof(*this))>0)
        {
            show_data(trip);
        }
    } /* end of while loop */
    infile.close();
    cout<<"*******************************************"<<endl;
    cout<<"***   End of List Records in the file   ***"<<endl;
    cout<<"*******************************************"<<endl;
} /* end of read_record */

void skitrip_record::search_record()
{
    int n;
    ifstream infile;
    infile.open(filename, ios::binary);
    if(!infile)
    {
        cout<<"\nError in opening! File Not Found!!"<<endl;
        return;
    }
    infile.seekg(0,ios::end);
    int count = infile.tellg()/sizeof(*this);
    cout<<"\n There are "<<count<<" record in the file";
    cout<<"\n Enter Game Record Number to Search: ";
    cin>>n;
    infile.seekg((n-1)*sizeof(*this));
    infile.read(reinterpret_cast<char*>(this), sizeof(*this));
    show_data(n);
} /* end of search_record */

void skitrip_record::edit_record()
{
    int n;
    fstream iofile;
    iofile.open(filename, ios::in|ios::binary);
    if(!iofile)
    {
        cout<<"\nError in opening! File Not Found!!"<<endl;
        return;
    }
    iofile.seekg(0, ios::end);
    int count = iofile.tellg()/sizeof(*this);
    cout<<"\n There are "<<count<<" games recorded in the file";
    cout<<"\n Enter Game Record Number to edit: ";
    cin>>n;
    iofile.seekg((n-1)*sizeof(*this));
    iofile.read(reinterpret_cast<char*>(this), sizeof(*this));
    cout<<"Ski Record "<<n<<" has following data"<<endl;
    show_data(n);
    iofile.close();
    iofile.open(filename, ios::out|ios::in|ios::binary);
    iofile.seekp((n-1)*sizeof(*this));
    cout<<"\nEnter data to Modify "<<endl;
    read_data();
    iofile.write(reinterpret_cast<char*>(this), sizeof(*this));
} /* end of edit_record */

void skitrip_record::delete_record()
{
    int n;
    ifstream infile;
    infile.open(filename, ios::binary);
    if(!infile)
    {
        cout<<"\nError in opening! File Not Found!!"<<endl;
        return;
    }
    infile.seekg(0,ios::end);
    int count = infile.tellg()/sizeof(*this);
    cout<<"\n There are "<<count<<" record in the file";
    cout<<"\n Enter Trip Record Number to Delete: ";
    cin>>n;
    fstream tmpfile;
    tmpfile.open("tmpfile.rec", ios::out|ios::binary);
    infile.seekg(0);
    for(int i=0; i<count; i++)
    {
        infile.read(reinterpret_cast<char*>(this),sizeof(*this));
        if(i==(n-1))
            continue;
        tmpfile.write(reinterpret_cast<char*>(this), sizeof(*this));
    } /* end of for loop */
    infile.close();
    tmpfile.close();
    remove(filename.c_str());
    rename("tmpfile.rec", filename.c_str());
} /* end of delete record */


int main()
{
    skitrip_record A;
    int choice;
    cout<<"\n*******************************************";
    cout<<"*** Ski Trip Record System              ***"<<endl;
    cout<<"\n*******************************************";
    while(true)
    {   /* Menu System */
	cout<<"\n*******************************************";
	cout<<"\n*                                         *";
        cout<<"\n*   Ski Trip Record System                *";
	cout<<"\n*                                         *";
        cout<<"\n*  Select one option below                *";
	cout<<"\n*                                         *";
        cout<<"\n*     1 - Add a SkiTrip                   *";
        cout<<"\n*     2 - Show SkiTrips                   *";
        cout<<"\n*     3 - Search SkiTrips                 *";
        cout<<"\n*     4 - Update SkiTrip                  *";
        cout<<"\n*     5 - Delete SkiTrip                  *";
        cout<<"\n*     6 - Generate Report                 *";
        cout<<"\n*     0 - Quit                            *";
	cout<<"\n*                                         *";
	cout<<"\n*******************************************";
        cout<<"\nEnter your choice: ";
        cin>>choice;
        switch(choice)
        {
            case 1:
                A.write_record();
                break;
            case 2:
                A.read_record();
                break;
            case 3:
                A.search_record();
                break;
            case 4:
                A.edit_record();
                break;
            case 5:
                A.delete_record();
                break;
            case 6:
                A.generate_report();
                break;
            case 0:
                exit(0);
                break;
            default:
                cout<<"\nEnter a correct number choice";
                exit(0);
        } /* end of switch */
    } /* end of while */
    cout<<"*** End of Ski Trip Record System              ***"<<endl;
    system("pause");
    return 0;
}

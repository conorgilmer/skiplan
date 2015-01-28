#!/usr/bin/python


#get user input

def getTripInfo():
  skiTripSet = []
  skiTripRecord = {}
  location = raw_input("Location of Trip? ")
  flights = float(raw_input("Cost of Flights? "))
  transfers = float(raw_input("Cost of Transfers? "))
  accommodation = float(raw_input("Cost of Accommodation? "))
  skipass = float(raw_input("Cost of Ski pass? "))
  skihire = float(raw_input("Cost of Ski hire? "))
  total = flights + transfers + accommodation + skihire + skipass
  print "Total Costs = ", total
  skiTripRecord["Location"] = location
  skiTripRecord["Flights"] = flights
  skiTripRecord["Transfers"] = transfers
  skiTripRecord["Accommodation"] = accommodation
  skiTripRecord["Ski pass"] = skipass
  skiTripRecord["Ski hire"] = skihire
  skiTripSet.append(skiTripRecord)
  return skiTripSet


def main():
  print " *** The Start *** "
  print "Ski Trip Costs"
  print getTripInfo()
  print " *** The End *** "

main()



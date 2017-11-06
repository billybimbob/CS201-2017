package lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import lab5.*; //used classes from previous lab;

public class CTAStopAppNew {

	private static CTARoute greenLine, redLine;
	
	public static void main(String[] args)  {
		Scanner kboard = new Scanner(System.in);
		try {
			File inFile = new File("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\lab6\\CTAStops.csv"); //file location varies based on computer
			Scanner input = new Scanner(inFile);
			
			input.nextLine(); //accounts for header
			input.useDelimiter(",|\\n"); //divides by commas and \n
			greenLine = new CTARoute("Green Line");
			redLine = new CTARoute("Red Line");
			//int count = 0; //counter reset
			
			while(input.hasNextLine()) {
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				String inLocation = input.next();
				boolean wheelChair = Boolean.parseBoolean(input.next()); //had to account for the \n on the last boolean, so used trim method
				//System.out.println(input.next());
				//System.out.println(input.next().trim());
				int redCheck = Integer.parseInt(input.next()), greenCheck = Integer.parseInt(input.next().trim());
				
				CTAStation station = new CTAStation(inName, inLat, inLong, inLocation, wheelChair, false);
				
				//both are if statements to account for stations that are both red and green line stations
				//arrayList is not in order of the indices of on the csv file
				if (redCheck != -1)
					redLine.addStation(station); //create object based on parses of the current line
				if (greenCheck != -1)
					greenLine.addStation(station);
				
				//count++;
			}
			
			System.out.println("Welcome to the CTA Green Line Information Center");
			userCommand:
			while(true) { //only breaks with the exit case in switch
				boolean haveAction = false;
				int choice = 0;
				System.out.println("\nPlease Select One of the Following Commands to Display Specific Information:");
				System.out.println("-----------------------------------------------");
				System.out.println(""
						+ "1. Display Station Names\n"
						+ "2. Display Stations with/without Wheelchair Access\n"
						+ "3. Display the Nearest Station\n"
						+ "4. Display Information for Specific Station\n"
						+ "5. Display all Station Information\n"
						+ "6. Add New Station\n"
						+ "7. Delete a Station\n"
						+ "8. Exit");
				System.out.println("-----------------------------------------------");
				do {
					try {
						System.out.print("Select which Number You want: ");
						choice = Integer.parseInt(kboard.nextLine());
					} catch (Exception e) {} //keeps program from crashing
					
					if ((choice <= 8 && choice > 0)) //Checks if input is valid
						haveAction = true;
					else
						System.out.println("\nInvalid Choice, Please Try Again\n");
				} while (!haveAction);
				
				System.out.println("");
				switch (choice) {
				case 1:
					displayStationNames();
					break;
				case 2:
					displayWheelchair(kboard);
					break;
				case 3:
					nearestStation(kboard);
					break;
				case 4:
					displaySpecific(kboard);
					break;
				case 5:
					displayAll();
					break;
				case 6:
					addStation(kboard);
					break;
				case 7:
					removeStation(kboard);
					break;
				case 8:
					break userCommand;
				}
				TimeUnit.SECONDS.sleep(2); //waits 2 seconds between each display of info
			}
			
			input.close();
		} catch (Exception e) { //program should automatically end if exception thrown
			System.out.println(e);
			System.out.println("Something went wrong");
		}
		System.out.println("The CTA Information Center Has Closed");
		kboard.close();
	}
	
	public static String lineCheck (Scanner keyboard, boolean both) { //method to ask for which Line is desired, accounts if more color are added
		String line = null;
		while(true) {
			String prompt = "Do you want the Red or Green line";
			if (both)
				prompt += " (you can choose both): ";
			else
				prompt += ": ";
			System.out.print(prompt);
			String lineIn = keyboard.nextLine().toLowerCase();
			//System.out.println(lineIn.equals("red"));
			if (lineIn.equals("red")) {
				line = "red";
				break;
			} else if (lineIn.equals("green")) {
				line = "green";
				break;
			} else if (both && lineIn.equals("green and red") || lineIn.equals("red and green") || lineIn.equals("both")) {
				line = "green and red";
				break;
			}
			System.out.println("Not a Valid Train Line");
		} //breaks out of loops if the input is a valid station
		
		return line;
	}
	
	//methods for each display of data
	public static void displayStationNames() { //goes through array and prints name
		ArrayList<CTAStation> redStops = redLine.getStops(), greenStops = greenLine.getStops();
		System.out.println("All of the Stations on the CTA Green Line are: ");
		for (int i = 0; i < redStops.size(); i++) {
			System.out.println(redStops.get(i).getName());
		}
		System.out.println("");
		System.out.println("All of the Stations on the CTA Red Line are: ");
		for (int i = 0; i < greenStops.size(); i++) {
			System.out.println(greenStops.get(i).getName());
		}
	}
	public static void displayWheelchair(Scanner keyboard) { //display wheelchair accessible or non-wheelchair accessible stations
		boolean searchWheel = false, haveResponse = false;
		do {
			System.out.println("Do You Want to Show Stations with Wheelchair Accessibility?");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = keyboard.nextLine().toCharArray();
			if (respStore.length == 1 && (respStore[0] == 'y' || respStore[0] == 'n')) { //checks if response is valid, looks for specifically y or n
				char response = respStore[0];
				searchWheel = response=='y';
				haveResponse = true;
			} else {
				System.out.println("Not a Valid Response\n");
			}
		} while (!haveResponse);
		
		int statFound = 0;
		String withOrOut;
		if (searchWheel) //change phrase based on response
			withOrOut = "with";
		else
			withOrOut = "without";
		System.out.println("\nHere are the Stations " + withOrOut + " Wheelchair Accessibility:");
		for (int i = 0; i < greenLine.getStops().size(); i++) { //goes through each station and checks the wheelchair boolean
			if (greenLine.getStops().get(i).getWheelchair() == searchWheel) { //prints station name if true
				System.out.println(greenLine.getStops().get(i).getName());
				statFound++;
			}
		}
		for (int i = 0; i < redLine.getStops().size(); i++) { //goes through each station and checks the wheelchair boolean
			if (redLine.getStops().get(i).getWheelchair() == searchWheel) { //prints station name if true
				System.out.println(redLine.getStops().get(i).getName());
				statFound++;
			}
		}
		if (statFound == 0)
			System.out.println("No Stations were Found");
	}
	public static void nearestStation (Scanner keyboard) { //displays nearby stations based on inputed location
		double curLat = 0, curLon = 0;
		boolean validLoc = false;
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			try {
				System.out.print("Enter your current latitude location: ");
				curLat = Double.parseDouble(keyboard.nextLine());
				System.out.print("Enter your current longitude location: ");
				curLon = keyboard.nextDouble();
				validLoc = true;
			} catch (Exception e) {
				System.out.println("Invalid Input\n");
			}
		} while (!validLoc); //breaks out of loops if the input is a double
		
		CTAStation nearRed = redLine.nearestStation(curLat, curLon), nearGreen = greenLine.nearestStation(curLat, curLon);
		
		if (nearRed.calcDistance(curLat, curLon) < nearGreen.calcDistance(curLat, curLon)) {
			System.out.println("The nearest station to you " + nearRed.getName());
		} else {
			System.out.println("The nearest station to you is " + nearGreen.getName() + " station");
		}
	}
	public static void displaySpecific (Scanner keyboard) {
		boolean validName = false, validRed = false, validGreen = false;
		CTAStation lookStation = null;
		String line = lineCheck(keyboard, true);
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			System.out.print("What station would you like the information of: ");
			String inLoc = keyboard.nextLine().toLowerCase();
			
			for (CTAStation i: greenLine.getStops()) {
				if (inLoc.equals(i.getName().toLowerCase()))
					validGreen = true;
			}
			for (CTAStation i: redLine.getStops()) {
				if (inLoc.equals(i.getName().toLowerCase()))
					validRed = true;
			}
				
			if (validRed && line.equals("red")) {
				lookStation = redLine.lookupStation(inLoc);
				validName = true;
				break;
			} else if (validGreen && line.equals("green")) {
				lookStation = greenLine.lookupStation(inLoc);
				validName = true;
				break;
			} else if (validGreen && validRed && line.equals("green and red")) {
				if (greenLine.lookupStation(inLoc).equals(redLine.lookupStation(inLoc))) {
					lookStation = greenLine.lookupStation(inLoc);
					validName = true;
					break;
				}
			}
			System.out.println("Not a Valid Station\n");
			
		} while (!validName); //breaks out of loops if the input is a valid station
		System.out.print(lookStation.toString());
	}
	public static void displayAll () {
		System.out.println("All of the information for the Green Line Stations:");
		System.out.println(greenLine.toString());
		System.out.println("");
		System.out.println("All of the information for the Red Line Stations:");
		System.out.println(redLine.toString());
	}
	
	public static int findIndex (Scanner keyboard, CTARoute searching) {
		int idx = -1;
		boolean validName = false, validIdx = false;
		do {
			String preName = null, sucName = null;
			do {
				boolean validSuc = false, validPre = false;
				System.out.print("What is the name of the preceeding Station: ");
				preName = keyboard.nextLine().toLowerCase();
				for (CTAStation i: searching.getStops()) {
					if (preName.equals(i.getName().toLowerCase()))
						validPre = true;
				}
				System.out.print("What is the name of the succeeding Station: ");
				sucName = keyboard.nextLine();
				for (CTAStation i: searching.getStops()) {
					if (sucName.equals(i.getName().toLowerCase()))
						validSuc = true;
				}
				if (!(validPre && validSuc))
					System.out.println("Not valid names");
				else
					validName = true;
			} while(!validName);
			int idxPre = -1, idxSuc = -1;
			for (int i = 0; i <= searching.getStops().size()-1; i++) {
				if (preName.equals(searching.getStops().get(i).getName().toLowerCase())) {
					idxPre = i;
					break;
				}
			}
			for (int i = 0; i <= searching.getStops().size()-1; i++) {
				if (sucName.equals(searching.getStops().get(i).getName().toLowerCase())) {
					idxSuc = i;
					break;
				}
			}
			if (idxPre+1 == idxSuc-1) {
				idx = idxPre+1;
				validIdx = true;
			} else
				System.out.println("The inputted stations don't have one station between");
			
		} while(!validIdx);
		return idx;
	}
	
	
	public static void addStation (Scanner keyboard) {
		boolean haveResponse = false;
		String line = lineCheck(keyboard, false);
		
		System.out.print("What is the name of the new Station: ");
		String name = keyboard.nextLine();
		
		double lat = 0, lon = 0;
		do {
			try {
				System.out.print("What is the latitude of the new Station: ");
				lat = Double.parseDouble(keyboard.nextLine());
				System.out.print("What is the longitude of the new Station: ");
				lon = Double.parseDouble(keyboard.nextLine());
				haveResponse = true;
			} catch (Exception e) {
				System.out.println("Not a valid input");
			}
		} while (!haveResponse);
		
		System.out.print("What is the location of the new Station: ");
		String location = keyboard.nextLine();
		
		boolean wheel = false;
		do {
			System.out.println("Is the the new Station wheelchair accessible");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = keyboard.nextLine().toCharArray();
			if (respStore.length == 1 && (respStore[0] == 'y' || respStore[0] == 'n')) { //checks if response is valid, looks for specifically y or n
				char response = respStore[0];
				wheel = response=='y';
				haveResponse = true;
			} else {
				System.out.println("Not a Valid Response\n");
			}
		} while (!haveResponse);
		
		if (line.equals("red")) {
			int index = findIndex(keyboard, redLine);
			redLine.insertStation(index, new CTAStation(name, lat, lon, location, wheel, true));
		} else if (line.equals("green")) {
			int index = findIndex(keyboard, greenLine);
			greenLine.insertStation(index, new CTAStation(name, lat, lon, location, wheel, true));
		}
		
	}
	public static void removeStation (Scanner keyboard) {
		boolean validRemove = false;
		String line = lineCheck(keyboard, true);
		do {
			System.out.print("What station would you like to remove: ");
			String statName = keyboard.nextLine().toLowerCase();
			
			if (line.equals("green")) {
				for (CTAStation i: greenLine.getStops()) {
					if (statName.equals(i.getName().toLowerCase())) {
						greenLine.removeStation(i);
						validRemove = true;
					}
				}
			} else if (line.equals("red")) {
				for (CTAStation i: redLine.getStops()) {
					if (statName.equals(i.getName().toLowerCase())) {
						redLine.removeStation(i);
						validRemove = true;
					}
				}
			} else if (line.equals("green and red")) {
				CTAStation greenHold = null, redHold = null;
				for (CTAStation i: greenLine.getStops()) {
					if (statName.equals(i.getName().toLowerCase())) {
						greenHold = i;
						validRemove = true;
					}
				}
				for (CTAStation i: redLine.getStops()) {
					if (statName.equals(i.getName().toLowerCase())) {
						redHold = i;
						validRemove = true;
					}
				}
				if (greenHold.equals(redHold)) {
					greenLine.removeStation(greenHold);
					redLine.removeStation(redHold);
				}
					
			}
			
		} while (!validRemove);
	}

}

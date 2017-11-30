package finalProject;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

//note: lab 5 classes referenced and modified
public class CTAStopAppFinal {

	//private static CTARoute greenLine, redLine;
	private static CTASystem system;
	private static Scanner keyboard;
	
	public static void main(String[] args)  {
		keyboard = new Scanner(System.in);
		try {
			File inFile = new File("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\finalProject\\CTAStops.csv"); //file location varies based on computer
			Scanner input = new Scanner(inFile);
			
			input.nextLine(); //accounts for header
			input.nextLine();
			input.useDelimiter(",|\\n"); //divides by commas and \n
			CTARoute //make each CTAline to store each station
			blueLine = new CTARoute("Blue Line"), brownLine = new CTARoute("Brown Line"), greenLine = new CTARoute("Green Line"),
			orangeLine = new CTARoute("Orange Line"), redLine = new CTARoute("Red Line"), pinkLine = new CTARoute("Pink Line"),
			purpleLine = new CTARoute("Purple Line"), yellowLine = new CTARoute("Yellow Line");
			
			while(input.hasNextLine()) {
				//parsing csv file
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				String inLocation = input.next();
				boolean wheelChair = Boolean.parseBoolean(input.next());
				
				int
				redCheck = Integer.parseInt(input.next()), greenCheck = Integer.parseInt(input.next()),
				blueCheck = Integer.parseInt(input.next()), brownCheck = Integer.parseInt(input.next()),
				purpleCheck = Integer.parseInt(input.next()), pinkCheck = Integer.parseInt(input.next()),
				orangeCheck = Integer.parseInt(input.next()), yellowCheck = Integer.parseInt(input.next().trim()); //account for \n
		
				CTAStation station = new CTAStation(inName, inLat, inLong, inLocation, wheelChair, false,
						blueCheck, brownCheck, greenCheck, pinkCheck, purpleCheck, orangeCheck, redCheck, yellowCheck); //instantiate object with parsed parameters
				
				//both are if statements to account for stations that are multiple lines
				if (blueCheck != -1)
					blueLine.addStation(station);
				if (brownCheck != -1)
					brownLine.addStation(station);
				if (greenCheck != -1)
					greenLine.addStation(station);
				if (orangeCheck != -1)
					orangeLine.addStation(station);	
				if (redCheck != -1)
					redLine.addStation(station); //create object based on parses of the current line
				if (pinkCheck != -1)
					pinkLine.addStation(station);
				if (purpleCheck != -1)
					purpleLine.addStation(station);
				if (yellowCheck != -1)
					yellowLine.addStation(station);
			}
			
			blueLine.sort("blue");
			brownLine.sort("brown");
			greenLine.sort("green");
			orangeLine.sort("orange");
			redLine.sort("red");
			pinkLine.sort("pink");
			purpleLine.sort("purple");
			yellowLine.sort("yellow");
					
			system = new CTASystem(blueLine, brownLine, greenLine, orangeLine, redLine, pinkLine, purpleLine, yellowLine); //not sure
			System.out.println("Welcome to the CTA Green Line Information Center");
			userCommand:
			while(true) { //only breaks with the exit case in switch
				boolean haveAction = false;
				int choice = 0;
				System.out.println("\n\nPlease Select One of the Following Commands to Display Specific Information:");
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
						choice = Integer.parseInt(keyboard.nextLine());
					} catch (Exception e) {} //keeps program from crashing
					
					if ((choice <= 8 && choice > 0)) //Checks if input is valid
						haveAction = true;
					else
						System.out.println("\nInvalid Choice, Please Try Again\n");
				} while (!haveAction);
				
				System.out.println("");
				switch (choice) { //performs method based on input
				case 1:
					displayStationNames();
					break;
				case 2:
					displayWheelchair();
					break;
				case 3:
					nearestStation();
					break;
				case 4:
					displaySpecific();
					break;
				case 5:
					displayAll();
					break;
				case 6:
					addStation();
					break;
				case 7:
					removeStation();
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
		keyboard.close();
	}
	
	//methods referenced by other CTAStop methods
	public static String lineCheck (boolean both) { //method to ask for which color Line is desired, accounts if more color are added, boolean if both is an option
		String line = null;
		while(true) {
			String prompt = "Do you want the Red or Green line";
			if (both)
				prompt += " (you can choose both): ";
			else
				prompt += ": ";
			System.out.print(prompt);
			String lineIn = keyboard.nextLine().toLowerCase();
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
	public static int findIndex (CTARoute searching) { //finds index based on preceding and succeeding stations
		int idx = -1;
		boolean validName = false, validIdx = false;
		do { //check if inputed names have one space between them
			String preName = null, sucName = null;
			int idxPre = -1, idxSuc = -1;
			do { //checks if inputed names are names on the station
				boolean validSuc = false, validPre = false;
				
				System.out.println("What is the name of the preceding Station");
				System.out.print("(type in 'beginning' if want to insert station at first index): ");
				preName = keyboard.nextLine().toLowerCase();
				if (preName.equals("beginning")) { //accounts for if inserted station were to be at the beginning: no preceding station
					idxPre = -1;
					validPre = true;
				}
				if (!validPre) {
					
					for (CTAStation i: searching.getStops()) { //checks if name is in the station list
						if (preName.equals(i.getName().toLowerCase()))
							validPre = true;
					}
				}
				
				System.out.println("What is the name of the succeeding Station (can be at the end)");
				System.out.print("(type in 'end' if want to insert station at last index): ");
				sucName = keyboard.nextLine();
				if (sucName.equals("end")) { //accounts for if inserted station were to be at the end: no succeeding station
					idxSuc = searching.getStops().size();
					validSuc = true;
				}
				if (!validSuc) {
					for (CTAStation i: searching.getStops()) { //same check as above
						if (sucName.equals(i.getName().toLowerCase()))
							validSuc = true;
					}
				}
				
				if (!(validPre && validSuc)) //check to break out of loop
					System.out.println("Not valid names");
				else
					validName = true;
					
			} while(!validName);
			
			//finds indices of inputed name
			boolean foundPre = false, foundSuc = false;
			for (int i = 0; i <= searching.getStops().size()-1; i++) {
				if (preName.equals(searching.getStops().get(i).getName().toLowerCase())) {
					idxPre = i;
					foundPre = true;
				} else if (sucName.equals(searching.getStops().get(i).getName().toLowerCase())) {
					idxSuc = i;
					foundSuc = true;
				}
				if (foundPre && foundSuc)
					break;
			}
		
			if (idxPre+1 == idxSuc-1) { //checks if one index is in between the preceding and succeeding
				idx = idxPre+1;
				validIdx = true;
			} else
				System.out.println("The inputed stations have more or less than one station between");
			
		} while(!validIdx);
		return idx;
	}

	//methods for each display of data
	public static void displayStationNames() { //goes through array and prints name
		for(CTAStation station: system.getStops())
			System.out.println(station.getName());
	}
	public static void displayWheelchair() { //display wheelchair accessible or non-wheelchair accessible stations
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
		
		for(CTAStation station: system.getStops()) {  //goes through each station and checks the wheelchair boolean
			if(station.getWheelchair()==searchWheel) { //prints station name if true
				System.out.println(station.getName());
				statFound++;
			}
		}
		
		if (statFound == 0)
			System.out.println("No Stations were Found");
	}
	public static void nearestStation () { //displays the nearest station to inputed latitude and longitude
		double curLat = 0, curLon = 0;
		boolean validLoc = false;
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			try {
				System.out.print("Enter your current latitude location: ");
				curLat = Double.parseDouble(keyboard.nextLine());
				System.out.print("Enter your current longitude location: ");
				curLon = Double.parseDouble(keyboard.nextLine());
				validLoc = true;
			} catch (Exception e) {
				System.out.println("Invalid Input\n");
			}
		} while (!validLoc); //breaks out of loops if the input is a double
		
		CTAStation nearest = system.nearestStation(curLat, curLon);
		System.out.println("The nearest station to you is " + nearest + " station");
		
	}
	public static void displaySpecific () { //displays instance variables of specified station
		boolean validName = false;
		String inLoc = null;
		
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			System.out.print("What station would you like the information of: ");
			inLoc = keyboard.nextLine().toLowerCase();
			for(CTAStation station: system.getStops()) {
				if (inLoc.equals(station.getName().toLowerCase())) {
					validName = true;
					break;
				}
			}
			if (!validName)
				System.out.println("Not a Valid Station\n");
					
		} while (!validName); //breaks out of loops if the input is a valid station
		
		CTAStation lookStation = system.lookupStation(inLoc);
		System.out.print(lookStation.toString());
	}
	public static void displayAll () { //calls toString method of both CTARoutes
		System.out.println("All of the information for CTA System Stations:");
		System.out.println(system.toString());
	}
	
	public static void addStation () { //inserts station at index of list with new CTAStation with inputed data variables
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
		
		System.out.print("What is the location of the new Station (elevated, subway, etc.): ");
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
			redLine.insertStation(index, new CTAStation(name, lat, lon, location, wheel, true, -1, index));
			System.out.println(name + " station was successfully added to the Red line");
		} else if (line.equals("green")) {
			int index = findIndex(keyboard, greenLine);
			greenLine.insertStation(index, new CTAStation(name, lat, lon, location, wheel, true, index, -1));
			System.out.println(name + " station was successfully added to the Green line");
		}
		
	}
	public static void removeStation () { //removes station with specified name from specified color line
		boolean validRemove = false;
		//String line = lineCheck(keyboard, true);
		do {
			System.out.print("What station would you like to remove: ");
			String statName = keyboard.nextLine().toLowerCase();
			
			//checks if name exists in list
			CTAStation removing = null;
			for (CTAStation i: system.getStops()) {
				if (statName.equals(i.getName().toLowerCase())) {
					removing = i;
					validRemove = true;
					break;
				}
			}
			if (removing.getBlueIdx()!=-1)
				system.getBlueLine().remove(removing);
			if (removing.getBrownIdx()!=-1)
				system.getBrownLine().remove(removing);
			if (removing.getGreenIdx()!=-1)
				system.getGreenLine().remove(removing);
			if (removing.getOrangeIdx()!=-1)
				system.getOrangeLine().remove(removing);
			if (removing.getPinkIdx()!=-1)
				system.getPinkLine().remove(removing);
			if (removing.getPurpleIdx()!=-1)
				system.getPurpleLine().remove(removing);
			
			
			if (!validRemove)
				System.out.println("Station(s) were not removed");
			
		} while (!validRemove);
		system.setStops();
		System.out.println("Station(s) successfully removed");
	}

}

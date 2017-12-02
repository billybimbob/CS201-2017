package finalProject;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

//note: lab 5 classes referenced and modified
public class CTAStopAppFinal {

	private static CTASystem system;
	//private static CTARoute blueLine, brownLine, greenLine, orangeLine, pinkLine, purpleLine, redLine, yellowLine;
	public static Scanner keyboard; //public because referenced in other classes
	public static final String[] lineColors = {"blue", "brown", "green", "orange", "pink", "purple", "red", "yellow"};
	
	public static void main(String[] args)  {
		keyboard = new Scanner(System.in);
		try {
			File inFile = new File("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\finalProject\\CTAStops.csv"); //file location varies based on computer
			Scanner input = new Scanner(inFile);
			
			input.nextLine(); //accounts for header
			input.nextLine();
			input.useDelimiter(",|\\n"); //divides by commas and \n
			
			//make each CTAline to store each station
			CTARoute[] lines = {
			new CTARoute("Blue Line"), new CTARoute("Brown Line"), new CTARoute("Green Line"), new CTARoute("Orange Line"),
			new CTARoute("Pink Line"), new CTARoute("Purple Line"), new CTARoute("Red Line"), new CTARoute("Yellow Line")};
			
			while(input.hasNextLine()) {
				//parsing csv file
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				String locCheck = input.next();
				Location inLocation = null;
				for (Location i: Location.values()) {
					if (locCheck.equals(i.toString()))
						inLocation = i;
				}
				if (inLocation==null)
					inLocation = null;
					
				boolean wheelChair = Boolean.parseBoolean(input.next());
				
				int
				redCheck = Integer.parseInt(input.next()), greenCheck = Integer.parseInt(input.next()),
				blueCheck = Integer.parseInt(input.next()), brownCheck = Integer.parseInt(input.next()),
				purpleCheck = Integer.parseInt(input.next()), pinkCheck = Integer.parseInt(input.next()),
				orangeCheck = Integer.parseInt(input.next()), yellowCheck = Integer.parseInt(input.next().trim()); //account for \n
				int[] indices = {blueCheck, brownCheck, greenCheck, orangeCheck, pinkCheck, purpleCheck, redCheck, yellowCheck};
				
				CTAStation station = new CTAStation(inName, inLat, inLong, inLocation, wheelChair, true,
						indices[0], indices[1], indices[2], indices[3], indices[4], indices[5], indices[6], indices[7]); //instantiate object with parsed parameters
				
				//both are if statements to account for stations that are multiple lines
				for (int i = 0; i < lineColors.length; i++) { //add to each list if index is not -1
					if (indices[i] != -1)
						lines[i].addStation(station);
				}
			}
			for (int i = 0; i < lineColors.length; i++) //sorts each list
				lines[i].sort(i);
					
			system = new CTASystem(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5], lines[6], lines[7]); //not sure
			System.out.println("Welcome to the CTA Green Line Information Center");
			userCommand:
			while(true) { //only breaks with the exit case in switch
				boolean haveAction = false;
				int choice = 0;
				System.out.println("\n\nPlease Select One of the Following Commands to Display Specific Information:");
				System.out.println("-----------------------------------------------");
				System.out.println(""
						+ "1. Display Information for All CTA Stations\n"
						+ "2. Display Information for Specific Station\n"
						+ "3. Add New Station\n"
						+ "4. Modify Existing Station\n"
						+ "5. Delete a Station\n"
						+ "6. Create Route\n"
						+ "7. Display the Nearest Station\n"
						+ "2. Display Stations with/without Wheelchair Access\n" //might get rid
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
					displayAll();
					break;
				case 2:
					displaySpecific();
					break;
				case 3:
					addStation();
					break;
				case 4:
					modifyStation();
					break;
				case 5:
					removeStation();
					break;
				case 6:
					
					break;
				case 7:
					nearestStation();
					break;
				case 8: //need to write to text file
					writeFile();
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
	public static ArrayList<CTAStation> lineCheck () { //method to ask for which color Line is desired, accounts if more color are added, boolean if both is an option
		ArrayList<CTAStation> line= null;
		
		validColor:
		while(true) {
			System.out.print("Which Line Color Do You Want: ");
			
			String lineIn = keyboard.nextLine().toLowerCase();
			switch(lineIn) {
			case "blue":
				line = system.getBlueLine();
				break validColor;
			case "brown":
				line = system.getBrownLine();
				break validColor;
			case "green":
				line = system.getGreenLine();
				break validColor;
			case "orange":
				line = system.getOrangeLine();
				break validColor;
			case "pink":
				line = system.getPinkLine();
				break validColor;
			case "purple":
				line = system.getPurpleLine();
				break validColor;
			case "red":
				line = system.getRedLine();
				break validColor;
			case "yellow":
				line = system.getYellowLine();
				break validColor;
			default:
				System.out.println("Not a Valid Train Line");	
			}
			
		} //breaks out of loops if the input is a valid station
		return line;
	}
	public static String validStation(String addPrompt) {
		boolean validName = false;
		String inLoc = null;
		
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			System.out.print("Enter in a station name " + addPrompt + ": ");
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
		
		return inLoc;
	}
	
	//methods in main
	public static void displayAll () { //calls toString method of both CTARoutes
		System.out.println("All of the information for CTA System Stations:");
		System.out.println(system.toString());
	}
	public static void displaySpecific () { //displays instance variables of specified station
		String inLoc = validStation("to show information");
		//System.out.println("---------------------------------------------------\n");
		System.out.println(system.lookupStation(inLoc).toString());
	}
	public static void addStation () { //inserts station at index of list with new CTAStation with inputed data variables
		boolean haveResponse = false;
		
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
		
		haveResponse = false;
		Location location = null;
		do {
			System.out.print("What is the location of the new Station (elevated, subway, etc.): ");
			String locCheck = keyboard.nextLine();
			for (Location i: Location.values()) {
				if (locCheck.equals(i.toString())) {
					location = i;
					haveResponse = true;
				}
			}
			if (!haveResponse)
				System.out.println("Not a valid location\n");
		} while (!haveResponse);
		
		haveResponse = false;
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
		
		haveResponse = false;
		ArrayList<ArrayList<CTAStation>> linesAdd = new ArrayList<ArrayList<CTAStation>>();
		do {
			ArrayList<CTAStation> line = lineCheck();
			boolean addCheck = true;
			for (ArrayList<CTAStation> i: linesAdd) {
				if (line.equals(i))
					addCheck = false;
			}
			
			if (addCheck)
				linesAdd.add(line);
			else
				System.out.println("Color line already picked");
			
			System.out.println("Do you want to add station to more color lines?");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = keyboard.nextLine().toCharArray();
			if (respStore[0] == 'n') //only checks if response starts with n
				haveResponse = true;
			
		} while(!haveResponse);
		
		CTAStation newStat = new CTAStation(name, lat, lon, location, wheel, true, -1, -1, -1, -1, -1, -1, -1, -1);
		for (int i = 0; i < linesAdd.size(); i++) {
			do {
				haveResponse = false;
				System.out.print("Enter the index for color line " + i + ": "); //could specify color?
				try {
					int index = Integer.parseInt(keyboard.nextLine());
					linesAdd.get(i).add(index, newStat);
					System.out.println(name + " station added to color line " + i);
					haveResponse = true;
				} catch(Exception e) {
					System.out.println("Not a valid index");
				}
			} while(!haveResponse);
		}
		System.out.println(name + " station was succssfully added");
		system.setStops();
	}
	public static void modifyStation () {
		String statName = validStation("to modify");
		CTAStation modStat = system.lookupStation(statName);
		
		modifying:
		while(true) {
			System.out.print(""
					+ "1. Name\n"
					+ "2. Location\n"
					+ "3. Opened\n"
					+ "4. Wheelchair\n"
					+ "5. Exit\n"
					+ "Which data do you want to modify (enter a number): ");
			try {
				int data = Integer.parseInt(keyboard.nextLine());
				switch (data) {
				case 1:
					String newName = keyboard.nextLine();
					modStat.setName(newName);
					break;
				case 2:
					boolean haveResponse = false;
					Location location = Location.name1;
					do {
						System.out.print("What is the location of the new Station (elevated, subway, etc.): ");
						String locCheck = keyboard.nextLine();
						for (Location i: Location.values()) {
							if (locCheck.equals(i.toString())) {
								location = i;
								haveResponse = true;
							}
						}
						if (!haveResponse)
							System.out.println("Not a valid location\n");
					} while (!haveResponse);
					
					modStat.setLocation(location);
					break;
				case 3:
					modStat.switchOpened();
					break;
				case 4:
					modStat.switchWheelchair();
					break;
				case 5:
					break modifying;
				}
				System.out.println("\nData successfully modified");
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		}
	}
	public static void removeStation () { //removes station with specified name from specified color line
		boolean validRemove = false;
		//String line = lineCheck(keyboard, true);
		do {
			String statName = validStation("to remove");
			
			//checks if name exists in list
			CTAStation removing = system.lookupStation(statName);
			if (removing!=null) { //which station do I want to remove
				validRemove = true;
				
				if (removing.getColorIdx("blue")!=-1) //change to int parameters?
					system.getBlueLine().remove(removing);
				if (removing.getColorIdx("brown")!=-1)
					system.getBrownLine().remove(removing);
				if (removing.getColorIdx("green")!=-1)
					system.getGreenLine().remove(removing);
				if (removing.getColorIdx("orange")!=-1)
					system.getOrangeLine().remove(removing);
				if (removing.getColorIdx("pink")!=-1)
					system.getPinkLine().remove(removing);
				if (removing.getColorIdx("purple")!=-1)
					system.getPurpleLine().remove(removing);
				if (removing.getColorIdx("yellow")!=-1)
					system.getYellowLine().remove(removing);
			} else {
				System.out.println("Station(s) were not removed");
			}
		} while (!validRemove);
		system.setStops();
		System.out.println("Station(s) successfully removed");
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
	public static void writeFile () { //bufferedwriter doesn't add \n
		try {
			FileWriter outFile = new FileWriter("CTA Train System.txt"); //set to the default file location
			
			BufferedWriter output = new BufferedWriter(outFile);
			output.write(system.toString()); //writes accumulated string to file
			
			output.close();
			System.out.println("The CTA System has been written to a text file");
		} catch (Exception e) {
			System.out.println("Something went wrong, file cannot be written");
		}
	}
	
}

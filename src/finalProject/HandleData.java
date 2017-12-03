package finalProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class HandleData { //interprets the inputted data

	private static ArrayList<String> addColors; //temporary?
	public static CTASystem system;
	public static final String[] lineColors = {"blue", "brown", "green", "orange", "pink", "purple", "red", "yellow"}; //public becasue referenced in other classes
	

	//methods referenced by other displaydata methods
	public static ArrayList<CTAStation> lineCheck () { //method to ask for which color Line is desired, accounts if more color are added, boolean if both is an option
		ArrayList<CTAStation> line= null;
		
		validColor:
		while(true) {
			System.out.print("Which Line Color Do You Want: ");
			
			String lineIn = keyboard.nextLine().toLowerCase();
			try {
				line = system.getColorLines(lineIn);
				addColors.add(lineIn);
				break validColor;
			} catch (Exception e) {
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
		addColors = new ArrayList<String>();
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
				System.out.print("Enter the index for " + addColors.get(i) + " line: "); //could specify color?
				try {
					int index = Integer.parseInt(keyboard.nextLine());
					linesAdd.get(i).add(index, newStat); //can't add to very last index
					System.out.println(name + " station added to " + addColors.get(i) + " line");
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
					System.out.print("What is the new name for the Station: ");
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
				
				for (String i: lineColors) {
					if (removing.getColorIdx(i)!=1)
						system.getColorLines(i).remove(removing);
				}
			} else {
				System.out.println("Station(s) were not removed");
			}
		} while (!validRemove);
		system.setStops();
		System.out.println("Station(s) successfully removed");
	}
	public static void createRoute() {
		String startName = validStation("to start at");
		CTAStation startStat = system.lookupStation(startName);
		String endName = validStation("for the destination");
		CTAStation endStat = system.lookupStation(endName);
		
		
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
		System.out.println("The nearest station to you:\n" + nearest);
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
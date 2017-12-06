package finalProject;

import java.util.ArrayList;

public class HandleData { //interprets and stores the inputed data

	private static ArrayList<String> addColors; //temporary?, list of line colors to add station to; instance variable because referenced in multiple methods
	public static CTASystem system; //has list of all the stations
	public static int[] systemCenter = {17, 25, 15, 13, 15, 19, 19, 2}; //index where stations all converge based on line
	public static final String[] lineColors = {"blue", "brown", "green", "orange", "pink", "purple", "red", "yellow"}; //public because referenced in other classes, list of all line colors
	

	//methods referenced by other displaydata methods
	public static ArrayList<CTAStation> lineCheck () { //method to ask for which color Line is desired
		ArrayList<CTAStation> line = null; //arraylist to return
		
		validColor:
		while(true) {
			System.out.print("Which Line Color Do You Want: ");
			
			String lineIn = CTAStopAppFinal.keyboard.nextLine().toLowerCase(); //user input
			try {
				line = system.getColorLines(lineIn);
				addColors.add(lineIn);
				break validColor;
			} catch (Exception e) { //exception if lineIn not a valid color
				System.out.println("Not a Valid Train Line");	
			}
		} //breaks out of loops if the input is a valid station
		return line;
	}
	public static String validStation(String addPrompt) { //gets user input and check if name is a name in list
		boolean validName = false;
		String inLoc = null;
		
		do {
			System.out.print("Enter in a station name " + addPrompt + ": "); //adds argument prompt to print
			inLoc = CTAStopAppFinal.keyboard.nextLine().toLowerCase();
			for(CTAStation station: system.getStops()) { //finds first instance of name in entire list
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
		String stationDisplay = "---------------------------------------------------\n" + system.lookupStation(inLoc).toString();
		System.out.println(stationDisplay);
	}
	public static void addStation () { //inserts station at index of list with new CTAStation with inputed data variables
		boolean haveResponse = false;
		//user inputs; each input loops until valid input found
		System.out.print("What is the name of the new Station: ");
		String name = CTAStopAppFinal.keyboard.nextLine();
		
		double lat = 0, lon = 0; //user input for location: latitude and longitude
		do {
			try {
				System.out.print("What is the latitude of the new Station: ");
				lat = Double.parseDouble(CTAStopAppFinal.keyboard.nextLine());
				System.out.print("What is the longitude of the new Station: ");
				lon = Double.parseDouble(CTAStopAppFinal.keyboard.nextLine());
				haveResponse = true;
			} catch (Exception e) {
				System.out.println("Not a valid input");
			}
		} while (!haveResponse);
		
		haveResponse = false;
		Location location = null;
		do { //user input for location
			System.out.print("What is the location of the new Station (elevated, subway, etc.): ");
			String locCheck = CTAStopAppFinal.keyboard.nextLine();
			for (Location i: Location.values()) { //checks if valid location enum value
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
		do { //user input for wheelchair accessibility
			System.out.println("Is the the new Station wheelchair accessible");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = CTAStopAppFinal.keyboard.nextLine().toCharArray();
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
		do { //user input to which color line to add to
			ArrayList<CTAStation> line = lineCheck();
			boolean addCheck = true;
			for (ArrayList<CTAStation> i: linesAdd) { //checks if list already added
				if (line.equals(i))
					addCheck = false;
			}
			
			if (addCheck && linesAdd.size()<lineColors.length-1)
				linesAdd.add(line);
			else if (linesAdd.size()==lineColors.length-1) { //checks if all color lines have been selected
				linesAdd.add(line);
				System.out.println("Station will be added to all Color Lines");
				break;
			} else
				System.out.println("Color line already picked");
			
			System.out.println("Do you want to add station to more color lines?");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = CTAStopAppFinal.keyboard.nextLine().toCharArray(); //checks if want to add more colors
			if (respStore[0] == 'n') //only checks if response starts with n
				haveResponse = true;
			
		} while(!haveResponse);
		
		CTAStation newStat = new CTAStation(name, lat, lon, location, wheel, true, -1, -1, -1, -1, -1, -1, -1, -1); //instantiate new station; indices set later
		for (int i = 0; i < linesAdd.size(); i++) {
			do { //user input for index on each respective color line
				haveResponse = false;
				System.out.print("Enter the index for " + addColors.get(i) + " line: ");
				try {
					int index = Integer.parseInt(CTAStopAppFinal.keyboard.nextLine());
					linesAdd.get(i).add(index, newStat); //can't add to very last index
					if (index <= systemCenter[i]) //changes hub station index
						systemCenter[i]++;
					
					System.out.println(name + " station added to " + addColors.get(i) + " line");
					haveResponse = true;
				} catch(Exception e) { //could have indxoutofbounds
					System.out.println("Not a valid index");
				}
			} while(!haveResponse);
		}
		System.out.println(name + " station was succssfully added");
		system.setStops(); //changes index of each station
	}
	public static void modifyStation () { //modify an existing station's data
		String statName = validStation("to modify");
		CTAStation modStat = system.lookupStation(statName);
		
		modifying:
		while(true) { //can modify the name, location, opened, wheelchair
			System.out.print(""
					+ "1. Name\n"
					+ "2. Location\n"
					+ "3. Opened\n"
					+ "4. Wheelchair\n"
					+ "5. Exit\n"
					+ "Which data do you want to modify (enter a number): ");
			try {
				int data = Integer.parseInt(CTAStopAppFinal.keyboard.nextLine());
				switch (data) {
				case 1: //change name with user input
					System.out.print("What is the new name for the Station: ");
					String newName = CTAStopAppFinal.keyboard.nextLine();
					modStat.setName(newName);
					break;
				case 2: //change location with user input
					boolean haveResponse = false;
					Location location = Location.name1;
					do {
						System.out.print("What is the location of the new Station (elevated, subway, etc.): ");
						String locCheck = CTAStopAppFinal.keyboard.nextLine();
						for (Location i: Location.values()) { //check if valid location
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
				case 3: //changes to opposite boolean
					modStat.switchOpened();
					break;
				case 4: //same as opened
					modStat.switchWheelchair();
					break;
				case 5: //breaks out of while loop
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
			String statName = validStation("to remove"); //checks if name exists in list
			CTAStation removing = system.lookupStation(statName); //specifies which station to remove
			if (removing!=null) {
				
				for (String i: lineColors) {
					if (removing.getColorIdx(i)!=-1 //removes station on color line
							&& !(removing.getColorIdx(i)==systemCenter[CTAStation.colorCheck(i)])) { //doesn't remove hub stations
						
						system.getColorLines(i).remove(removing);
						validRemove = true;
						
						if (removing.getColorIdx(i) < systemCenter[CTAStation.colorCheck(i)])
							systemCenter[CTAStation.colorCheck(i)]--;
						
					} else if (removing.getColorIdx(i)==systemCenter[CTAStation.colorCheck(i)])
						System.out.println("Error, cannot remove central station");
					
				}
			} else { //not sure if ever reached
				System.out.println("Station(s) were not removed");
			}
		} while (!validRemove);
		system.setStops();
		System.out.println("Station(s) successfully removed");
	}
	public static void createRoute() { //creates route based on user input for start and end station
		ArrayList<CTAStation> direction = null;
		while(true) {
			direction = new ArrayList<CTAStation>(); //list of stations to get from start to destination
			String startName = validStation("to start at");
			CTAStation startStat = system.lookupStation(startName); //gets starting station
			String endName = validStation("for the destination");
			CTAStation endStat = system.lookupStation(endName); //gets destination station
			if (!(startStat.equals(endStat))) { //checks if start and destination are the same
				direction.add(startStat);
				direction.add(endStat);
				break;
			} else
				System.out.println("Error, same station");
		}
		System.out.println("\nHere are the directions:");
		
		direction = system.formDirection(direction);
		//interprets list of stations
		String[] colorTrans = new String[direction.size()-1];
		for (int i = 0; i < colorTrans.length; i++) { //creates list of color transfer between each station in list
			for (int j = 0; j < lineColors.length; j++) {
				if (direction.get(i+1).getColorIdx(j)!=-1 && direction.get(i).getColorIdx(j)!=-1) {
					char capital = (char)(lineColors[j].charAt(0)-32);
					colorTrans[i] = capital + lineColors[j].substring(1);
					break;
				}
			}
		}
		for (int i = 0; i < colorTrans.length-1; i++) { //fills in null gaps, occur when transfer from lake to state/lake
			if (colorTrans[i]==null && colorTrans[i+1]!=null)
				colorTrans[i] = colorTrans[i+1];
		}
		/*
		for (String i: colorTrans)
			System.out.println(i);
		*/
		int sameCount = 0, stepCount = 1;
		for(int i = 0; i < direction.size()-1; i++) {
			sameCount = i+1; //index in direction list for station to transfer to
			while (sameCount < direction.size()-2 && colorTrans[sameCount].equals(colorTrans[i])) { //accounts for transfers to same color line
				sameCount++;
			}
			String ending = null;
			if (i==direction.size()-2 || sameCount==direction.size()-1) //checks if final station to arrive
				ending = "Arrive";
			else
				ending = "Transfer to " + colorTrans[sameCount] + " Line";
			
			//System.out.println("sameCount" + direction.get(sameCount));
			//System.out.println("i" + direction.get(i));
			boolean addStep = false;
			for (int j = 0; j < lineColors.length; j++) {
				if (direction.get(sameCount).getColorIdx(j)!=-1 && direction.get(i).getColorIdx(j)!=-1 //prints if both stations are on color line
						|| sameCount==direction.size()-1 && direction.get(sameCount).getColorIdx(j)!=-1) { //under assumption 1st color match is one correct
					int numStops = 0; //number of stations between the two stations
					CTAStation fromStat = null; //station to transfer from; can be hub station or i index
					if ((direction.get(sameCount).getColorIdx(j)==-1 || direction.get(i).getColorIdx(j)==-1) //hub station fromStat
							&& sameCount==direction.size()-1) { //hub station can go to any color line, and can go straight to destination
						numStops = Math.abs(systemCenter[j]-direction.get(sameCount).getColorIdx(j));
						fromStat = system.getColorLines(j).get(systemCenter[j]);
					} else { //i index
						numStops = Math.abs((direction.get(sameCount).getColorIdx(j)-direction.get(i).getColorIdx(j)));
						fromStat = direction.get(i);
					}
						
					System.out.println(stepCount + ". From " + fromStat.getName() + " Station - Ride for " + numStops
							+ " stop(s)\n   " + ending + " at " + direction.get(sameCount).getName() + " Station");
					addStep = true;
					break;
				}
			}
			i+=(sameCount-i)-1; //increments i based on transfer stations
			if (addStep) //counter for ui step number
				stepCount++;
		}
	}
	public static void nearestStation () { //displays the nearest station to inputed latitude and longitude
		double curLat = 0, curLon = 0; //user input variables
		boolean validLoc = false;
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			try {
				System.out.print("Enter your current latitude location: ");
				curLat = Double.parseDouble(CTAStopAppFinal.keyboard.nextLine());
				System.out.print("Enter your current longitude location: ");
				curLon = Double.parseDouble(CTAStopAppFinal.keyboard.nextLine());
				validLoc = true;
			} catch (Exception e) {
				System.out.println("Invalid Input\n");
			}
		} while (!validLoc); //breaks out of loops if the input is a double
		
		CTAStation nearest = system.nearestStation(curLat, curLon);
		System.out.println("The nearest station to you:\n" + nearest);
	}
	
}
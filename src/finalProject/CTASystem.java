package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	//list of each station list, each color list ordered alphabetically on color name
	private ArrayList<ArrayList<CTAStation>> colorLines  =  new ArrayList<ArrayList<CTAStation>>(); //blue, brown, green, orange, pink, purple, red, yellow
	
	//constructors
	public CTASystem() {
		this.setName("Chicago Transit System");
		for (int i = 0; i < HandleData.lineColors.length; i++)
			colorLines.add(new ArrayList<CTAStation>());
		
		stops = getStops();
	}
	public CTASystem(CTARoute line1, CTARoute line2, CTARoute line3, CTARoute line4,
			CTARoute line5, CTARoute line6, CTARoute line7, CTARoute line8) {
		
		this.setName("Chicago Transit System");
		CTARoute[] routeStore = {line1, line2, line3, line4, line5, line6, line7, line8}; //temporary list for all CTARoute arguments
		for (CTARoute i: routeStore)  //sets list of list to each respective line color
			colorLines.add(i.getStops());
		stops = getStops(); //sets to instance variable
	}

	//getters
	public ArrayList<CTAStation> getColorLines(int color) { //gets arrayList at argument index
		return colorLines.get(color);
	}
	public ArrayList<CTAStation> getColorLines(String color) { //converts string to int index, calls other getter
		int colorNum = CTAStation.colorCheck(color);
		return getColorLines(colorNum);
	}
	public ArrayList<CTAStation> getStops() { //returns collective list of all station lists
		ArrayList<CTAStation> allStation = new ArrayList<CTAStation>();
		for (String i: HandleData.lineColors)
			allStation.addAll(getColorLines(i));
		
		return allStation;
	}
	
	public String toString() { //loops through all lists and gets toString of each station
		String list = "";
		for (String color: HandleData.lineColors) {
			char upperCase = (char)(color.charAt(0) - 32); //based on character difference between upper and lower being 32
			list += "\n" + upperCase + color.substring(1) + " Line: \n---------------------------------------------------\n";
			for (CTAStation station: getColorLines(color))
				list += station.toString() + "\n";
		}
		
		return list;
	}

	//setters
	public void setIndices(int color) { //goes through one of the lists and alters each station index to match the index in the list; accounts for alterations to the list
		for (int i = 0; i < getColorLines(color).size(); i++)
			getColorLines(color).get(i).setColorIdx(color, i);
	}
	public void setStops() { //calls setIndices method for all color lines
		for (int i = 0; i < HandleData.lineColors.length; i++) {
			setIndices(i);
		}
		stops = this.getStops();
	}
	
	//methods called in route formation method
	public CTAStation searchLine (ArrayList<CTAStation> searching, int color, int startIdx) { //finds the next station with multiple color lines
		CTAStation multiStat = null; // station returning
		do {
			//System.out.println("got here");
			CTAStation statCheck = null; //station to check if on multiple color lines
			if (startIdx > HandleData.systemCenter[color]) //check move torwards "hub" station on each color; hub station can transfer to all colors
				statCheck = searching.get(--startIdx);
			else
				statCheck = searching.get(++startIdx);
			
			boolean nearCenter = false; //checks if station is near the "hub" station
			for (int i = startIdx-1; i < startIdx+2; i++) {
				if (i==HandleData.systemCenter[color])
					nearCenter = true;
			}
				
			if (statCheck.getNumLines() > 1 && !nearCenter || startIdx == HandleData.systemCenter[color]) //returns station that has multiple color lines and not near center or is the hub station
				multiStat = statCheck;
		} while(multiStat == null);
		
		//System.out.println(multiStat);
		return multiStat;
	}
	public ArrayList<CTAStation> possibleStations (CTAStation refStation) { //returns list of all multiple stations near inputed station
		ArrayList<CTAStation> possibleStations = new ArrayList<CTAStation>(); //list of possible stations to return
		
		for (int i = 0; i < refStation.getColorIdx().length; i++) { //checks on which color line to call searchLine
			if (refStation.getColorIdx()[i]!=-1) {
				//System.out.println(HandleData.systemCenter[i] + " " + refStation.getColorIdx(i));
				possibleStations.add(searchLine(getColorLines(i), i, refStation.getColorIdx(i)));
			}
		}
		return possibleStations;
	}
	public boolean sameLine (CTAStation station1, CTAStation station2) { //checks if stations are on same line
		boolean sameLine = false; //default not on sameLine
		//System.out.println("\nstation1" + station1 + "\nstation2" + station2);
		for (int j = 0; j < HandleData.lineColors.length; j++) { //loops through all color lines
			if (station1.getColorIdx(j)!=-1 && station2.getColorIdx(j)!=-1) //check for same color line
				sameLine = true;
		}
		
		return sameLine;
	}
	
	//route formation method						//direction parameter should be two stations
	public ArrayList<CTAStation> formDirection(ArrayList<CTAStation> direction) { //adds list of stations to get first index to last index
		//control for while loop
		boolean directionFound = false, forward = true; //boolean to alternate between searching for a station at the starting station and destination
		int countFor = 0, countBack = 1; //indices for searching stations
		do {
			int count = 0; //index for each instance of check; either the countFor or countBack, based on forward boolean
			if (forward)
				count = countFor;
			else
				count = direction.size()-countBack;
			
			//System.out.println("start\n" + direction.get(countFor) + "\nend\n" + direction.get(direction.size()-countBack));
			boolean sameLine = sameLine(direction.get(countFor), direction.get(direction.size()-countBack)); //checks if on sameLine, thus forming route
			
			boolean centerStation = false; //checks if added station is at hub station, and can then transfer to any other station
			for (int i = 0; i < direction.get(count).getColorIdx().length; i++) {
				if (direction.get(count).getColorIdx(i) == HandleData.systemCenter[i])
					centerStation = true;
			}
				
			if (sameLine || centerStation)
				directionFound = true; //will break out of loop
			else { //searching for station to transfer to

				ArrayList<CTAStation> possibleStats = possibleStations(direction.get(count)); //all possible stations that could be added to route

				CTAStation addRoute = null; //station to add
				int highestColor = 0;

				for (CTAStation i: possibleStats) { //determines which of the possible stations to add
					if ((forward && sameLine(i, direction.get(direction.size()-countBack)))
							|| (!forward && sameLine(i, direction.get(countFor)))) { //adds if on same color line as next station
						addRoute = i;
						directionFound = true;
						break; //breaks out of for loop
					}
					if (i.getNumLines()>highestColor) { //the station with the highest amount of color lines will be added
						addRoute = i;
						highestColor = i.getNumLines();
					}
				}
				direction.add(direction.size()-countBack, addRoute); //
				if (forward)
					countFor++;
				else
					countBack++;
				
				forward = !forward;
			}
		} while(!directionFound);
		
		return direction;
	}
}

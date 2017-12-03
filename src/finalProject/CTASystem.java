package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private ArrayList<ArrayList<CTAStation>> colorLines  =  new ArrayList<ArrayList<CTAStation>>(); //blue, brown, green, orange, pink, purple, red, yellow
	
	public CTASystem() {
		this.setName("Chicago Transit System");
		for (int i = 0; i < HandleData.lineColors.length; i++)
			colorLines.add(new ArrayList<CTAStation>());
		
		stops = getStops();
	}
	public CTASystem(CTARoute line1, CTARoute line2, CTARoute line3, CTARoute line4,
			CTARoute line5, CTARoute line6, CTARoute line7, CTARoute line8) { //intentional shallow copy (not shallow copies)
		
		this.setName("Chicago Transit System");
		CTARoute[] routeStore = {line1, line2, line3, line4, line5, line6, line7, line8};
		for (CTARoute i: routeStore)
			colorLines.add(i.getStops());
		stops = getStops();
	}

	//getters
	public ArrayList<CTAStation> getColorLines(int color) {
		return colorLines.get(color);
	}
	public ArrayList<CTAStation> getColorLines(String color) {
		int colorNum = CTAStation.colorCheck(color);
		return getColorLines(colorNum);
	}
	public ArrayList<CTAStation> getStops() {
		ArrayList<CTAStation> allStation = new ArrayList<CTAStation>();
		for (String i: HandleData.lineColors)
			allStation.addAll(getColorLines(i));
		
		return allStation;
	}
	
	public String toString() {
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
	public void setIndices(int color) {
		for (int i = 0; i < getColorLines(color).size(); i++)
			getColorLines(color).get(i).setColorIdx(color, i);
	}
	public void setStops() {
		for (int i = 0; i < HandleData.lineColors.length; i++) {
			setIndices(i);
		}
		stops = this.getStops();
	}
	
	public CTAStation searchLine (ArrayList<CTAStation> searching, int color, int startIdx) { //finds the next station with multiple color lines
		CTAStation multiStat = null;
		do {
			//System.out.println("got here");
			CTAStation statCheck = null;
			if (startIdx > HandleData.systemCenter[color])
				statCheck = searching.get(--startIdx);
			else
				statCheck = searching.get(++startIdx);
			
			if (statCheck.getNumLines() > 1 || startIdx == HandleData.systemCenter[color])
				multiStat = statCheck;
		} while(multiStat == null);
		
		return multiStat;
	}
	public ArrayList<CTAStation> possibleStations (CTAStation refStation) { //returns list of all multiple stations near inputed station
		ArrayList<CTAStation> possibleStations = new ArrayList<CTAStation>();
		
		for (int i = 0; i < refStation.getColorIdx().length; i++) {
			if (refStation.getColorIdx()[i]!=-1) {
				System.out.println(HandleData.systemCenter[i]);
				possibleStations.add(searchLine(getColorLines(i), i, refStation.getColorIdx(i)));
			}
		}
		return possibleStations;
	}
	public boolean sameLine (CTAStation station1, CTAStation station2) { //checks if stations are on same line
		boolean sameLine = false;
		for (int j = 0; j < HandleData.lineColors.length; j++) {
			if (station1.getColorIdx(j)!=-1 && station2.getColorIdx(j)!=-1)
				sameLine = true;
		}
		
		return sameLine;
	}
																//direction parameter should be two stations
	public ArrayList<CTAStation> formDirection(ArrayList<CTAStation> direction) { //adds list of stations to get first index to last index
		boolean directionFound = false, forward = true;
		int countFor = 0, countBack = 1;
		do {
			for(CTAStation i: direction)
				System.out.println(i);
			int count = 0;
			if (forward)
				count = countFor;
			else
				count = direction.size()-countBack;
			
			boolean sameLine = sameLine(direction.get(countFor), direction.get(direction.size()-countBack));

			if (sameLine)
				directionFound = true;
			else {

				ArrayList<CTAStation> possibleStats = possibleStations(direction.get(count));

				CTAStation addRoute = null;
				int highestColor = 1;

				for (CTAStation i: possibleStats) {
					if (sameLine(i, direction.get(direction.size()-1))) {
						addRoute = i;
						directionFound = true;
						break;
					}
					
					if (i.getNumLines()>highestColor) {
						addRoute = i;
						highestColor = i.getNumLines();
					}
				}
				direction.add(direction.size()-1, addRoute);
				if (forward)
					countFor++;
				else
					countBack++;
				System.out.println("got here");
				forward = !forward;
			}
		} while(!directionFound);
		
		return direction;
	}
}

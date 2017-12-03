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
	
	public CTAStation searchLine (ArrayList<CTAStation> searching, boolean oneWay, int startIdx) { //finds the next station with multiple color lines
		CTAStation multiStat = null;
		do {
			CTAStation statCheck = null;
			if (oneWay) {
				statCheck = searching.get(++startIdx);
			} else {
				if (startIdx > searching.size()/2)
					statCheck = searching.get(--startIdx);
				else
					statCheck = searching.get(++startIdx);
			}
			
			if (statCheck.getNumLines() > 1)
				multiStat = statCheck;
		} while(multiStat == null);
		
		return multiStat;
	}
	public boolean sameLine (CTAStation station1, CTAStation station2) {
		boolean sameLine = false;
		for (int j = 0; j < HandleData.lineColors.length; j++) {
			if (station1.getColorIdx(j)!=-1 && station2.getColorIdx(j)!=-1)
				sameLine = true;
		}
		
		return sameLine;
	}
																//direction parameter should be two stations
	public ArrayList<CTAStation> formDirection(ArrayList<CTAStation> direction) { //adds list of stations to get first index to last index
		boolean directionFound = false;
		int count = 0;
		do {
			boolean sameLine = sameLine(direction.get(count), direction.get(direction.size()-1));
			/*ArrayList<String> colorSearch = new ArrayList<String>();
			for (int j = 0; j < HandleData.lineColors.length; j++) {
				if (direction.get(count).getColorIdx(j)!=-1) {
					colorSearch.add(HandleData.lineColors[j]);
					
					if (direction.get(direction.size()-1).getColorIdx(j)!=-1) {
						endColor.add(HandleData.lineColors[j]);
						sameLine = true;
					}
				}
			}*/
			
			if (sameLine)
				directionFound = true;
			else {
				ArrayList<CTAStation> possibleStats = new ArrayList<CTAStation>();
				for (int i: direction.get(count).getColorIdx()) {
					if (i!=-1) {
						boolean oneWay = true;
						if (HandleData.lineColors[i]=="blue" || HandleData.lineColors[i]=="green" || HandleData.lineColors[i]=="red")
							oneWay = false;
						possibleStats.add(searchLine(getColorLines(i), oneWay, direction.get(count).getColorIdx(i)));
					}
				}
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
				count++;
			}
		} while(!directionFound);
		
		return direction;
	}
}

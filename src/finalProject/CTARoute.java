package finalProject;

import java.util.ArrayList;

public class CTARoute {

	private String name;
	protected ArrayList<CTAStation> stops; //protected since no setter
	
	//constructors
	public CTARoute() { //default
		name = "default";
		stops = new ArrayList<CTAStation>();
	}
	public CTARoute(String name) {
		this.name = name;
		stops = new ArrayList<CTAStation>();
	}
	
	//getters
	public String getName() {
		return name;
	}
	public ArrayList<CTAStation> getStops() {
		return stops;
	}
	
	public String toString() {
		String list = "";
		for (CTAStation station: stops) {
			list += station.toString() + "\n";
			list += "---------------------------------------------------\n";
		}
		return list;
	}
	public boolean equals(CTARoute comparing) { //compares name and list
		boolean listCompare = true;
		if (this.stops.size() == comparing.getStops().size()) { //checks if stops length is the same
			for (int i = 0; i < stops.size(); i++) { //checks each station if equal
				if (this.stops.get(i).equals(comparing.getStops().get(i)))
					listCompare = false;
			}
		} else
			listCompare = false;
		
		return this.getName().equals(comparing.getName()) && listCompare;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	//list methods of stops ArrayList
	public void addStation(CTAStation adding) {
		stops.add(adding);
	}
	public void removeStation(CTAStation removing) {
		stops.remove(removing);
	}
	public void insertStation(int indx, CTAStation adding) {
		stops.add(indx,  adding);
	}
	public void sort(String lineColor) {
		int[] idxStore = new int[stops.size()];
		
		switch(lineColor) { //makes int array based on color of line
		case "blue":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getBlueIdx();
			break;
		case "brown":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getBrownIdx();
			break;
		case "green":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getGreenIdx();
			break;
		case "orange":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getOrangeIdx();
			break;
		case "pink":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getPinkIdx();
			break;
		case "purple":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getPurpleIdx();
			break;
		case "red":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getRedIdx();
			break;
		case "yellow":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getYellowIdx();
			break;
		}
		
		for (int i = 0; i < stops.size()-1; i++) {
			int j = i+1;
			/*
			switch(lineColor) {
			case "blue":
				station1 = stops.get(j).getBlueIdx();
				station2 = stops.get(j-1).getBlueIdx();
				break;
			case "brown":
				station1 = stops.get(j).getBrownIdx();
				station2 = stops.get(j-1).getBrownIdx();
				break;
			case "green":
				station1 = stops.get(j).getGreenIdx();
				station2 = stops.get(j-1).getGreenIdx();
				break;
			case "orange":
				station1 = stops.get(j).getOrangeIdx();
				station2 = stops.get(j-1).getOrangeIdx();
				break;
			case "pink":
				station1 = stops.get(j).getPinkIdx();
				station2 = stops.get(j-1).getPinkIdx();
				break;
			case "purple":
				station1 = stops.get(j).getPurpleIdx();
				station2 = stops.get(j-1).getPurpleIdx();
				break;
			case "red":
				station1 = stops.get(j).getRedIdx();
				station2 = stops.get(j-1).getRedIdx();
				break;
			case "yellow":
				station1 = stops.get(j).getYellowIdx();
				station2 = stops.get(j-1).getYellowIdx();
				break;
			}*/
			
			while(j>=1 && idxStore[j] < idxStore[j-1]) {
				CTAStation store = stops.get(j);
				stops.set(j, stops.get(j-1));
				stops.set(j-1, store);
				j-=1;
			}
		}
	}
	/*
	public void sort(String lineColor) { //sorts the stops list in ascending index of the inputed line color
		int count = 0;
		while (count < stops.size()-1) {
			int sortVal = -1, sortVal2 = -1;
			switch(lineColor) { //determines the index
			case "blue":
				sortVal = stops.get(count).getBlueIdx();
				sortVal2 = stops.get(count+1).getBlueIdx();
				break;
			case "brown":
				sortVal = stops.get(count).getBrownIdx();
				sortVal2 = stops.get(count+1).getBrownIdx();
				break;
			case "green":
				sortVal = stops.get(count).getGreenIdx();
				sortVal2 = stops.get(count+1).getGreenIdx();
				break;
			case "orange":
				sortVal = stops.get(count).getOrangeIdx();
				sortVal2 = stops.get(count+1).getOrangeIdx();
				break;
			case "red":
				sortVal = stops.get(count).getRedIdx();
				sortVal2 = stops.get(count+1).getRedIdx();
				break;
			case "pink":
				sortVal = stops.get(count).getPinkIdx();
				sortVal2 = stops.get(count+1).getPinkIdx();
				break;
			case "purple":
				sortVal = stops.get(count).getPurpleIdx();
				sortVal2 = stops.get(count+1).getPurpleIdx();
				break;
			case "yellow":
				sortVal = stops.get(count).getYellowIdx();
				sortVal2 = stops.get(count+1).getYellowIdx();
				break;
			}
			if (sortVal > sortVal2) { //starts forward, if greater, going to send to end of list
				
				for (int i = stops.size()-1; i >= 0; i--) {
					switch(lineColor) { //same as above
					case "green":
						sortVal2 = stops.get(i).getGreenIdx();
						break;
					case "red":
						sortVal2 = stops.get(i).getRedIdx();
						break;
					}
					if (sortVal > sortVal2) { //works backwards to determine where to switch
						stops.add(i, stops.remove(count));
						count = 0; //resets to beginning of list
						break;
					}
				}
			} else {
				count += 1;
			}
		}
	}
	*/
	public CTAStation lookupStation(String looking) { //returns first instance of CTAStation matching the inputed name parameter
		CTAStation match = null;
		for (CTAStation station: stops) {
			if (station.getName().toLowerCase().equals(looking.toLowerCase())) {
				match = station;
				break;
			}
		}
		return match; //null is checked before this method is called
			
	}
	public CTAStation nearestStation(double lat, double lon) { //returns nearest distance to location parameter in stops arraylist
		CTAStation nearest = stops.get(0);
		for (int i = 1; i < stops.size(); i++) {
			if (stops.get(i).calcDistance(lat, lon) < nearest.calcDistance(lat, lon)) { //not sure what the threshold for nearby distance is
				nearest = stops.get(i);
			}
		}
		return nearest;
	}
	public CTAStation nearestStation(GeoLocation loc) { //same as above method with different parameters
		CTAStation nearest = stops.get(0);
		for (int i = 1; i < stops.size(); i++) {
			if (stops.get(i).calcDistance(loc) < nearest.calcDistance(loc)) { //not sure what the threshold for nearby distance is
				nearest = stops.get(i);
			}
		}
		return nearest;
	}

}

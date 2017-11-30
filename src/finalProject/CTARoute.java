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
		
		switch(lineColor) { //makes int array based on color of line, parallel array
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
			
			while(j>=1 && idxStore[j] < idxStore[j-1]) {
				CTAStation store = stops.get(j);
				int storeNum = idxStore[j];
				
				stops.set(j, stops.get(j-1));
				idxStore[j] = idxStore[j-1];
				
				stops.set(j-1, store);
				idxStore[j-1] = storeNum;
				
				j-=1;
			}
		}
	}
	
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

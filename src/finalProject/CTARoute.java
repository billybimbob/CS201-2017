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
				idxStore[i] = stops.get(i).getColorIdx("blue");
			break;
		case "brown":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("brown");
			break;
		case "green":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("green");
			break;
		case "orange":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("orange");
			break;
		case "pink":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("pink");
			break;
		case "purple":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("purple");
			break;
		case "red":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("red");
			break;
		case "yellow":
			for(int i = 0; i < stops.size(); i++)
				idxStore[i] = stops.get(i).getColorIdx("yellow");
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
	
	public ArrayList<CTAStation> lookupStation(String looking) { //returns first instance of CTAStation matching the inputed name parameter
		ArrayList<CTAStation> matches = new ArrayList<CTAStation>();
		for (CTAStation station: stops) {
			if (station.getName().toLowerCase().equals(looking.toLowerCase())) {
				
				//System.out.println(station);
				boolean sameStation = false;
				for (CTAStation checkStation: matches) {
					if (station.equals(checkStation)) {
						sameStation = true;
						//System.out.println("got here");
					}
				}
				if (!sameStation)
					matches.add(station);
			}
		}
		return matches; //null is checked before this method is called
			
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

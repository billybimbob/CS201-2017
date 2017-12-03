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
	public void setIndices(int color) { //changes index variable of stations to match list indices (account for add and remove
		for (int i = 0; i < stops.size(); i++)
			stops.get(i).setColorIdx(color, i);
	}
	public void setIndices (String color) {
		int colorNum = CTAStation.colorCheck(color);
		this.setIndices(colorNum);
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
	public void sort(int color) {
		int[] idxStore = new int[stops.size()];
		
		for(int i = 0; i < stops.size(); i++)
			idxStore[i] = stops.get(i).getColorIdx(color); 
		
		for (int i = 0; i < stops.size()-1; i++) { //insertion sort
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
		ArrayList<CTAStation> matchList = new ArrayList<CTAStation>();
		CTAStation match = null;
		for (CTAStation station: stops) {
			if (station.getName().toLowerCase().equals(looking.toLowerCase())) {
				
				//System.out.println(station);
				boolean sameStation = false;
				for (CTAStation checkStation: matchList) {
					if (station.equals(checkStation)) {
						sameStation = true;
						//System.out.println("got here");
					}
				}
				if (!sameStation)
					matchList.add(station);
			}
		}
		
		if (matchList.size()>1) { //if multiple stations with same name found
			int[] colors = new int[CTAStopAppFinal.lineColors.length]; //number of color lines on CTA
			for (CTAStation i: matchList) {
				for (int j = 0; j < colors.length; j++) { //makes list of color lines in matchList
					if (i.getColorIdx(j)!=-1)
						colors[j]++;
				}
			}
			boolean hasResponse = false;
			do { //gets user input
				int count = 0;
				for (int i = 0; i < colors.length; i++) {
					for (int j = 0; j < colors[i]; j++) {
						count++;
						char upperCase = (char)(CTAStopAppFinal.lineColors[i].charAt(0)-32);
						System.out.println(count + ". " + upperCase + CTAStopAppFinal.lineColors[i].substring(1));
					}
				}
				System.out.print("There are multiple stations with that name"
						+ "\nSelect a line color (enter in a number): ");
				try {
					int num = Integer.parseInt(CTAStopAppFinal.keyboard.nextLine());
					match = matchList.get(num-1);
					hasResponse = true;
				} catch(Exception e) {
					System.out.println("Not a valid number");
				}
			} while(!hasResponse);
		} else
			match = matchList.get(0);
		
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

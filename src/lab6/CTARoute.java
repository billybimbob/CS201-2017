package lab6;

import lab5.*;
import java.util.ArrayList;

public class CTARoute {

	private String name;
	private ArrayList<CTAStation> stops;
	
	public CTARoute() {
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
	
	//list methods
	public void addStation(CTAStation adding) {
		stops.add(adding);
	}
	public void removeStation(CTAStation removing) {
		stops.remove(removing);
	}
	public void insertStation(int indx, CTAStation adding) {
		stops.set(indx, adding);
	}
	public CTAStation lookupStation(String looking) {
		CTAStation match = null;
		for (CTAStation station: stops) {
			if (station.getName() == looking) {
				match = station;
				break;
			}
		}
		return match; //need to add if null is returned
	}
	public CTAStation nearestStation(double lat, double lon) {
		CTAStation nearest = stops.get(0);
		for (int i = 1; i < stops.size(); i++) {
			if (stops.get(i).calcDistance(lat, lon) < nearest.calcDistance(lat, lon)) { //not sure what the threshold for nearby distance is
				nearest = stops.get(i);
			}
		}
		return nearest;
	}
	public CTAStation nearestStation(GeoLocation loc) {
		CTAStation nearest = stops.get(0);
		for (int i = 1; i < stops.size(); i++) {
			if (stops.get(i).calcDistance(loc) < nearest.calcDistance(loc)) { //not sure what the threshold for nearby distance is
				nearest = stops.get(i);
			}
		}
		return nearest;
	}

}

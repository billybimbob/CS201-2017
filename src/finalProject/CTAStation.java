package finalProject;

public class CTAStation extends GeoLocation {

	private String name, location;
	private boolean opened, wheelchair;
	private int greenIdx, redIdx;
	
	public CTAStation() { //default constructor
		super();
		name = "Dummy";
		location = "underground";
		wheelchair = false;
		opened = false;
		greenIdx = -1;
		redIdx = -1;
	}
	public CTAStation(String name, double latitude, double longitude, String location, boolean wheelchair, boolean opened, 
			int greenIdx, int redIdx) {
		super(latitude, longitude);
		this.name = name;
		this.location = location;
		this.wheelchair = wheelchair;
		this.opened = opened;
		this.greenIdx = greenIdx;
		this.redIdx = redIdx;
	}
	
	//getters
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public boolean getWheelchair() {
		return wheelchair;
	}
	public boolean getOpened() {
		return opened;
	}
	public int getGreenIdx() {
		return greenIdx;
	}
	public int getRedIdx() {
		return redIdx;
	}
	
	//setters, the boolean values can only be 2 values, so made a switch method to make current boolean value the opposite
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void switchWheelchair() {
		wheelchair = !wheelchair;
	}
	public void switchOpened() {
		opened = !opened;
	}
	
	//prints all variables
	public String toString() {
		String baseState = name + " Station\n" + super.toString() + "\nLocation - " + location;
		String wheelState = null;
		if (wheelchair)
			wheelState = "Wheelchair accessibility is present";
		else
			wheelState = "Wheelchair accessibility is not present";
		return baseState + "\n" + wheelState;
	}
	
	//checks if name, latitude and longitude are the same
	public boolean equals (CTAStation station2) {
		return (this.getName().equals(station2.getName())
				&& this.getLatitude()==station2.getLatitude()
				&& this.getLongitude()==station2.getLongitude());
	}

}

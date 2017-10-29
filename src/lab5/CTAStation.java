package lab5;

public class CTAStation extends GeoLocation {

	private String name, location;
	private boolean opened, wheelchair;
	
	public CTAStation() { //default constructor
		super();
		name = "Dummy";
		location = "underground";
		wheelchair = false;
		opened = false;
	}
	public CTAStation(String name, double latitude, double longitude, String location, boolean wheelchair, boolean opened) {
		super(latitude, longitude);
		this.name = name;
		this.location = location;
		this.wheelchair = wheelchair;
		this.opened = opened;
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
	
	//checks if name, latitude and longitude are the same
	public boolean equals (CTAStation station2) {
		return (this.getName()==station2.getName() && this.getLatitude()==station2.getLatitude() && this.getLongitude()==station2.getLongitude());
	}

}

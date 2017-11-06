package lab5;

public class GeoLocation {

	private double latitude, longitude;
	
	public GeoLocation() { //default constructor
		latitude = 0;
		longitude = 0;
	}
	public GeoLocation(double latitude, double longitude) { //constructor with inputed lat and long
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//getters
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	
	//setters, don't have any checks to see if changed values are valid
	public void setLatitude(double newLat) {
		this.latitude = newLat;
	}
	public void setLongitude(double newLong) {
		this.longitude = newLong;
	}
	
	//equals method, checks if latitude and longitude are equal
	public boolean equals(double latitude, double longitude) {
		return (this.latitude==latitude && this.longitude==longitude);
	}
	public boolean equals(GeoLocation location2) {
		return (this.getLatitude()==location2.getLatitude() && this.getLongitude()==location2.getLongitude());
	}
	
	//to String by printing out the latitude and longitude
	public String toString() {
		return "Latitude - " + latitude + " \tLongitude - " + longitude;
	}

	//calculate distance with the distance formula
	public double calcDistance(GeoLocation location2) {
		double distance = Math.sqrt(Math.pow(location2.getLatitude()-this.latitude, 2)+Math.pow(location2.getLongitude()-this.longitude, 2));
		return distance;
	}
	public double calcDistance(double latitude2, double longitude2) {
		double distance = Math.sqrt(Math.pow(latitude2-this.latitude, 2)+Math.pow(longitude2-this.longitude, 2));
		return distance;
	}
	
}

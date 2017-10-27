package lab5;

public class GeoLocation {

	private double latitude, longitude;
	
	public GeoLocation() {
		latitude = 1;
		longitude = 1;
	}
	public GeoLocation(double latitude, double longitude) {
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

	public double calcDistance(GeoLocation location2) {
		double distance = Math.sqrt(Math.pow(location2.getLatitude()-this.latitude, 2)+Math.pow(location2.getLongitude()-this.longitude, 2));
		return distance;
	}
	public double calcDistance(double latitude2, double longitude2) {
		double distance = Math.sqrt(Math.pow(latitude2-this.latitude, 2)+Math.pow(longitude2-this.longitude, 2));
		return distance;
	}
	
}

package lab5;

public class CTAStation extends GeoLocation {

	private String name, location;
	private boolean opened, wheelchair;
	
	public CTAStation(String name, double latitude, double longitude, boolean wheelchair, boolean opened) {
		super(latitude, longitude);
		this.name = name;
		this.wheelchair = wheelchair;
		this.opened = opened;
	}

}

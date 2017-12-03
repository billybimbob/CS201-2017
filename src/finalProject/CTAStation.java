package finalProject;

public class CTAStation extends GeoLocation {

	private String name;
	private Location location;
	private boolean opened, wheelchair;
	private int[] colorIdx; //blue, brown, green, orange, pink, purple, red, yellow
	
	public CTAStation() { //default constructor
		super();
		name = "Dummy";
		location = Location.name1;
		wheelchair = false;
		opened = false;
		int[] idxStore = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
		colorIdx = idxStore;
	}
	public CTAStation(String name, double latitude, double longitude, Location location, boolean wheelchair, boolean opened, 
			int blueIdx, int brownIdx, int greenIdx, int orangeIdx, 
			int pinkIdx, int purpleIdx, int redIdx, int yellowIdx) {
		super(latitude, longitude);
		this.name = name;
		this.location = location;
		this.wheelchair = wheelchair;
		this.opened = opened;
		int[] idxStore = {blueIdx, brownIdx, greenIdx, orangeIdx, pinkIdx, purpleIdx, redIdx, yellowIdx};
		colorIdx = idxStore;
	}
	
	//getters
	public String getName() {
		return name;
	}
	public Location getLocation() {
		return location;
	}
	public boolean getWheelchair() {
		return wheelchair;
	}
	public boolean getOpened() {
		return opened;
	}
	public int getColorIdx(int colorNum) {
		return colorIdx[colorNum];
	}
	public int getColorIdx(String color) {
		int colorNum = colorCheck(color);
		return getColorIdx(colorNum);
	}
	
	//setters, the boolean values can only be 2 values, so made a switch method to make current boolean value the opposite
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void switchWheelchair() {
		wheelchair = !wheelchair;
	}
	public void switchOpened() {
		opened = !opened;
	}
	public void setColorIdx(int color, int changeIdx) {
		this.colorIdx[color] = changeIdx;
	}
	
	//prints all variables
	public String toString() {
		String margin = "---------------------------------------------------";
		String baseState = name + " Station\n" + super.toString() + "\nLocation - " + location;
		String wheelState = null, colorState = "Line Colors - ";
		if (wheelchair)
			wheelState = "Wheelchair Accessibility - present";
		else
			wheelState = "Wheelchair Accessibility - not present";
		
		for (String color: HandleData.lineColors) {
			if (this.getColorIdx(color)!=-1) {
				char upperCase = (char)(color.charAt(0) - 32); //based on character difference between upper and lower being 32
				colorState += (upperCase + color.substring(1) + "  ");
			}
		}
		
		return baseState + "\n" + wheelState + "\n" + colorState + "\n" + margin;
	}
	
	//checks if name, latitude and longitude are the same
	public boolean equals (CTAStation station2) {
		return (this.getName().equals(station2.getName())
				&& this.getLatitude()==station2.getLatitude()
				&& this.getLongitude()==station2.getLongitude());
	}
	
	public static int colorCheck(String color) { //converts color name to color index
		int colorNum = -1;
		for (int i = 0; i < HandleData.lineColors.length; i++) {
			if (color.equals(HandleData.lineColors[i])) {
				colorNum = i;
				break;
			}
		}
		
		return colorNum;
	}

}

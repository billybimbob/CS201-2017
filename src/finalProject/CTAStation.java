package finalProject;

public class CTAStation extends GeoLocation {

	private String name;
	private Location location;
	private boolean opened, wheelchair;
	//private int blueIdx, brownIdx, greenIdx, orangeIdx, pinkIdx, purpleIdx, redIdx, yellowIdx; //make into an array?
	private int[] colorIdx; //blue, brown, green, orange, pink, purple, red, yellow
	
	public CTAStation() { //default constructor
		super();
		name = "Dummy";
		location = Location.name1;
		wheelchair = false;
		opened = false;/*
		blueIdx = -1;
		brownIdx = -1;
		greenIdx = -1;
		orangeIdx = -1;
		pinkIdx = -1;
		purpleIdx = -1;
		redIdx = -1;
		yellowIdx = -1;*/
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
		this.opened = opened;/*
		this.blueIdx = blueIdx;
		this.brownIdx = brownIdx;
		this.greenIdx = greenIdx;
		this.orangeIdx = orangeIdx;
		this.pinkIdx = pinkIdx;
		this.purpleIdx = purpleIdx;
		this.redIdx = redIdx;
		this.yellowIdx = yellowIdx;*/
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
	}/*
	public int getBlueIdx() {
		return blueIdx;
	}
	public int getBrownIdx() {
		return brownIdx;
	}
	public int getGreenIdx() {
		return greenIdx;
	}
	public int getOrangeIdx() {
		return orangeIdx;
	}
	public int getPinkIdx() {
		return pinkIdx;
	}
	public int getPurpleIdx() {
		return purpleIdx;
	}
	public int getRedIdx() {
		return redIdx;
	}
	public int getYellowIdx() {
		return yellowIdx;
	}*/
	public int getColorIdx(String color) {
		int colorNum = colorCheck(color);
		return colorIdx[colorNum];
	}
	public int getColorIdx(int colorNum) {
		return colorIdx[colorNum];
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
		String baseState = name + " Station\n" + super.toString() + "\nLocation - " + location;
		String wheelState = null, colorState = "Line Colors - ";
		if (wheelchair)
			wheelState = "Wheelchair Accessibility - present";
		else
			wheelState = "Wheelchair Accessibility - not present";
		
		if (this.getColorIdx("blue")!=-1)
			colorState+="Blue  ";
		if (this.getColorIdx("brown")!=-1)
			colorState+="Brown  ";
		if (this.getColorIdx("green")!=-1)
			colorState+="Green  ";
		if (this.getColorIdx("orange")!=-1)
			colorState+="Orange  ";
		if (this.getColorIdx("pink")!=-1)
			colorState+="Pink  ";
		if (this.getColorIdx("purple")!=-1)
			colorState+="Purple  ";
		if (this.getColorIdx("red")!=-1)
			colorState+="Red  ";
		if (this.getColorIdx("yellow")!=-1)
			colorState+="Yellow  ";
		
		return baseState + "\n" + wheelState + "\n" + colorState;
	}
	
	//checks if name, latitude and longitude are the same
	public boolean equals (CTAStation station2) {
		return (this.getName().equals(station2.getName())
				&& this.getLatitude()==station2.getLatitude()
				&& this.getLongitude()==station2.getLongitude());
	}
	
	public int colorCheck(String color) { //converts color name to color index
		int colorNum = -1;
		switch(color) {
		case "blue":
			colorNum = 0;
			break;
		case "brown":
			colorNum = 1;
			break;
		case "green":
			colorNum = 2;
			break;
		case "orange":
			colorNum = 3;
			break;
		case "pink":
			colorNum = 4;
			break;
		case "purple":
			colorNum = 5;
			break;
		case "red":
			colorNum = 6;
			break;
		case "yellow":
			colorNum = 7;
			break;
		}
		
		return colorNum;
	}

}

package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private static ArrayList<CTAStation> blueLine, brownLine, greenLine, orangeLine, redLine, pinkLine, purpleLine, yellowLine;
	
	public CTASystem() {
		this.setName("Chicago Transit System");
		blueLine = new ArrayList<CTAStation>();
		brownLine = new ArrayList<CTAStation>();
		greenLine = new ArrayList<CTAStation>();
		orangeLine = new ArrayList<CTAStation>();
		redLine = new ArrayList<CTAStation>();
		pinkLine = new ArrayList<CTAStation>();
		purpleLine = new ArrayList<CTAStation>();
		yellowLine = new ArrayList<CTAStation>();
	}
	public CTASystem(CTARoute line1, CTARoute line2, CTARoute line3, CTARoute line4,
			CTARoute line5, CTARoute line6, CTARoute line7, CTARoute line8) { //intentional shallow copy
		this.setName("Chicago Transit System");
		blueLine = new ArrayList<CTAStation>(line1.getStops());
		brownLine = new ArrayList<CTAStation>(line2.getStops());
		greenLine = new ArrayList<CTAStation>(line3.getStops());
		orangeLine = new ArrayList<CTAStation>(line4.getStops());
		redLine = new ArrayList<CTAStation>(line5.getStops());
		pinkLine = new ArrayList<CTAStation>(line6.getStops());
		purpleLine = new ArrayList<CTAStation>(line7.getStops());
		yellowLine = new ArrayList<CTAStation>(line8.getStops());
	}

	//getters
	public static ArrayList<CTAStation> getBlueLine() {
		return blueLine;
	}
	public static ArrayList<CTAStation> getBrownLine() {
		return brownLine;
	}
	public static ArrayList<CTAStation> getGreenLine() {
		return greenLine;
	}
	public static ArrayList<CTAStation> getOrangeLine() {
		return orangeLine;
	}
	public static ArrayList<CTAStation> getRedLine() {
		return redLine;
	}
	public static ArrayList<CTAStation> getPinkLine() {
		return pinkLine;
	}
	public static ArrayList<CTAStation> getPurpleLine() {
		return purpleLine;
	}
	public static ArrayList<CTAStation> getYellowLine() {
		return yellowLine;
	}
	public static ArrayList<CTAStation> getAll() {
		ArrayList<CTAStation> allStation = new ArrayList<CTAStation>();
		allStation.addAll(blueLine);
		allStation.addAll(brownLine);
		allStation.addAll(greenLine);
		allStation.addAll(orangeLine);
		allStation.addAll(pinkLine);
		allStation.addAll(purpleLine);
		allStation.addAll(yellowLine);
		return allStation;
	}

	//setters
	public static void setBlueLine(ArrayList<CTAStation> blueLine) {
		CTASystem.blueLine = blueLine;
	}
	public static void setBrownLine(ArrayList<CTAStation> brownLine) {
		CTASystem.brownLine = brownLine;
	}
	public static void setGreenLine(ArrayList<CTAStation> greenLine) {
		CTASystem.greenLine = greenLine;
	}
	public static void setOrangeLine(ArrayList<CTAStation> orangeLine) {
		CTASystem.orangeLine = orangeLine;
	}
	public static void setRedLine(ArrayList<CTAStation> redLine) {
		CTASystem.redLine = redLine;
	}
	public static void setPinkLine(ArrayList<CTAStation> pinkLine) {
		CTASystem.pinkLine = pinkLine;
	}
	public static void setPurpleLine(ArrayList<CTAStation> purpleLine) {
		CTASystem.purpleLine = purpleLine;
	}
	public static void setYellowLine(ArrayList<CTAStation> yellowLine) {
		CTASystem.yellowLine = yellowLine;
	}
	
	

}

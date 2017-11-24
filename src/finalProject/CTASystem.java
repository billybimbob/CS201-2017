package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private static ArrayList<CTAStation> blueLine, brownLine, greenLine, orangeLine, redLine, pinkLine, purpleLine, yellowLine;
	
	public CTASystem() {
		this.setName("Chicago Transit System");
		setBlueLine(new ArrayList<CTAStation>());
		setBrownLine(new ArrayList<CTAStation>());
		setGreenLine(new ArrayList<CTAStation>());
		setOrangeLine(new ArrayList<CTAStation>());
		setRedLine(new ArrayList<CTAStation>());
		setPinkLine(new ArrayList<CTAStation>());
		setPurpleLine(new ArrayList<CTAStation>());
		setYellowLine(new ArrayList<CTAStation>());
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

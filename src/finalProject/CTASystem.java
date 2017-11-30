package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private ArrayList<CTAStation> blueLine, brownLine, greenLine, orangeLine, redLine, pinkLine, purpleLine, yellowLine;
	
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
		stops = getAll();
	}
	public CTASystem(CTARoute line1, CTARoute line2, CTARoute line3, CTARoute line4,
			CTARoute line5, CTARoute line6, CTARoute line7, CTARoute line8) { //intentional shallow copy (not shallow copies)
		this.setName("Chicago Transit System");
		blueLine = new ArrayList<CTAStation>(line1.getStops());
		brownLine = new ArrayList<CTAStation>(line2.getStops());
		greenLine = new ArrayList<CTAStation>(line3.getStops());
		orangeLine = new ArrayList<CTAStation>(line4.getStops());
		redLine = new ArrayList<CTAStation>(line5.getStops());
		pinkLine = new ArrayList<CTAStation>(line6.getStops());
		purpleLine = new ArrayList<CTAStation>(line7.getStops());
		yellowLine = new ArrayList<CTAStation>(line8.getStops());
		stops = getAll();
	}

	//getters
	public ArrayList<CTAStation> getBlueLine() {
		return blueLine;
	}
	public ArrayList<CTAStation> getBrownLine() {
		return brownLine;
	}
	public ArrayList<CTAStation> getGreenLine() {
		return greenLine;
	}
	public ArrayList<CTAStation> getOrangeLine() {
		return orangeLine;
	}
	public ArrayList<CTAStation> getRedLine() {
		return redLine;
	}
	public ArrayList<CTAStation> getPinkLine() {
		return pinkLine;
	}
	public ArrayList<CTAStation> getPurpleLine() {
		return purpleLine;
	}
	public ArrayList<CTAStation> getYellowLine() {
		return yellowLine;
	}
	public ArrayList<CTAStation> getAll() {
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
	public void setBlueLine(ArrayList<CTAStation> blueLine) {
		this.blueLine = blueLine;
	}
	public void setBrownLine(ArrayList<CTAStation> brownLine) {
		this.brownLine = brownLine;
	}
	public void setGreenLine(ArrayList<CTAStation> greenLine) {
		this.greenLine = greenLine;
	}
	public void setOrangeLine(ArrayList<CTAStation> orangeLine) {
		this.orangeLine = orangeLine;
	}
	public void setRedLine(ArrayList<CTAStation> redLine) {
		this.redLine = redLine;
	}
	public void setPinkLine(ArrayList<CTAStation> pinkLine) {
		this.pinkLine = pinkLine;
	}
	public void setPurpleLine(ArrayList<CTAStation> purpleLine) {
		this.purpleLine = purpleLine;
	}
	public void setYellowLine(ArrayList<CTAStation> yellowLine) {
		this.yellowLine = yellowLine;
	}
	public void setStops() {
		stops = this.getAll();
	}
	

}

package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private ArrayList<CTAStation> blueLine, brownLine, greenLine, orangeLine, pinkLine, purpleLine, redLine, yellowLine; //divides stops into separate parts
	
	public CTASystem() {
		this.setName("Chicago Transit System");
		blueLine = new ArrayList<CTAStation>();
		brownLine = new ArrayList<CTAStation>();
		greenLine = new ArrayList<CTAStation>();
		orangeLine = new ArrayList<CTAStation>();
		pinkLine = new ArrayList<CTAStation>();
		purpleLine = new ArrayList<CTAStation>();
		redLine = new ArrayList<CTAStation>();
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
		pinkLine = new ArrayList<CTAStation>(line5.getStops());
		purpleLine = new ArrayList<CTAStation>(line6.getStops());
		redLine = new ArrayList<CTAStation>(line7.getStops());
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
	public ArrayList<CTAStation> getPinkLine() {
		return pinkLine;
	}
	public ArrayList<CTAStation> getPurpleLine() {
		return purpleLine;
	}
	public ArrayList<CTAStation> getRedLine() {
		return redLine;
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
		allStation.addAll(redLine);
		allStation.addAll(yellowLine);
		return allStation;
	}
	
	public String toString() {
		String list = "";
		list += "\nBlue Line: \n---------------------------------------------------\n";
		for (CTAStation station: blueLine)
			list += station.toString() + "\n";
		list += "\nBrown Line: \n---------------------------------------------------\n";
		for (CTAStation station: brownLine)
			list += station.toString() + "\n";
		list += "\nGreen Line: \n---------------------------------------------------\n";
		for (CTAStation station: greenLine)
			list += station.toString() + "\n";
		list += "\nOrange Line: \n---------------------------------------------------\n";
		for (CTAStation station: orangeLine)
			list += station.toString() + "\n";
		list += "\nPink Line: \n---------------------------------------------------\n";
		for (CTAStation station: pinkLine)
			list += station.toString() + "\n";
		list += "\nPurple Line: \n---------------------------------------------------\n";
		for (CTAStation station: purpleLine)
			list += station.toString() + "\n";
		list += "\nRed Line: \n---------------------------------------------------\n";
		for (CTAStation station: redLine)
			list += station.toString() + "\n";
		list += "\nYellow Line: \n---------------------------------------------------\n";
		for (CTAStation station: yellowLine)
			list += station.toString() + "\n";
		
		return list;
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
	public void setPinkLine(ArrayList<CTAStation> pinkLine) {
		this.pinkLine = pinkLine;
	}
	public void setPurpleLine(ArrayList<CTAStation> purpleLine) {
		this.purpleLine = purpleLine;
	}
	public void setRedLine(ArrayList<CTAStation> redLine) {
		this.redLine = redLine;
	}
	public void setYellowLine(ArrayList<CTAStation> yellowLine) {
		this.yellowLine = yellowLine;
	}
	public void setIndices(int color) {
		switch(color) {
		case 0:
			for (int i = 0; i < blueLine.size(); i++)
				blueLine.get(i).setColorIdx(color, i);
			break;
		case 1:
			for (int i = 0; i < brownLine.size(); i++)
				brownLine.get(i).setColorIdx(color, i);
			break;
		case 2:
			for (int i = 0; i < greenLine.size(); i++)
				greenLine.get(i).setColorIdx(color, i);
			break;
		case 3:
			for (int i = 0; i < orangeLine.size(); i++)
				orangeLine.get(i).setColorIdx(color, i);
			break;
		case 4:
			for (int i = 0; i < pinkLine.size(); i++)
				pinkLine.get(i).setColorIdx(color, i);
			break;
		case 5:
			for (int i = 0; i < purpleLine.size(); i++)
				purpleLine.get(i).setColorIdx(color, i);
			break;
		case 6:
			for (int i = 0; i < redLine.size(); i++)
				redLine.get(i).setColorIdx(color, i);
			break;
		case 7:
			for (int i = 0; i < yellowLine.size(); i++)
				yellowLine.get(i).setColorIdx(color, i);
			break;
		}
	}
	public void setStops() {
		for (int i = 0; i < CTAStopAppFinal.lineColors.length; i++) {
			setIndices(i);
		}
		stops = this.getAll();
	}
	

}

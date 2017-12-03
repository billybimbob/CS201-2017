package finalProject;

import java.util.*;

public class CTASystem extends CTARoute {

	private ArrayList<ArrayList<CTAStation>> colorLines  =  new ArrayList<ArrayList<CTAStation>>(); //blue, brown, green, orange, pink, purple, red, yellow
	
	public CTASystem() {
		this.setName("Chicago Transit System");
		for (int i = 0; i < HandleData.lineColors.length; i++)
			colorLines.add(new ArrayList<CTAStation>());
		
		stops = getStops();
	}
	public CTASystem(CTARoute line1, CTARoute line2, CTARoute line3, CTARoute line4,
			CTARoute line5, CTARoute line6, CTARoute line7, CTARoute line8) { //intentional shallow copy (not shallow copies)
		
		this.setName("Chicago Transit System");
		CTARoute[] routeStore = {line1, line2, line3, line4, line5, line6, line7, line8};
		for (CTARoute i: routeStore)
			colorLines.add(i.getStops());
		stops = getStops();
	}

	//getters
	public ArrayList<CTAStation> getColorLines(int color) {
		return colorLines.get(color);
	}
	public ArrayList<CTAStation> getColorLines(String color) {
		int colorNum = CTAStation.colorCheck(color);
		return getColorLines(colorNum);
	}
	public ArrayList<CTAStation> getStops() {
		ArrayList<CTAStation> allStation = new ArrayList<CTAStation>();
		for (String i: HandleData.lineColors)
			allStation.addAll(getColorLines(i));
		
		return allStation;
	}
	
	public String toString() {
		String list = "";
		for (String color: HandleData.lineColors) {
			char upperCase = (char)(color.charAt(0) - 32); //based on character difference between upper and lower being 32
			list += "\n" + upperCase + color.substring(1) + " Line: \n---------------------------------------------------\n";
			for (CTAStation station: getColorLines(color))
				list += station.toString() + "\n";
		}
		
		return list;
	}

	//setters
	public void setIndices(int color) {
		for (int i = 0; i < getColorLines(color).size(); i++)
			getColorLines(color).get(i).setColorIdx(color, i);
	}
	public void setStops() {
		for (int i = 0; i < HandleData.lineColors.length; i++) {
			setIndices(i);
		}
		stops = this.getStops();
	}
	
}

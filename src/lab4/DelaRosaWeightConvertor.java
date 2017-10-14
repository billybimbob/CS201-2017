package lab4;

public class DelaRosaWeightConvertor {

	private int lbs; //only one instance variable
	
	public DelaRosaWeightConvertor() { //default constructor
		lbs = 0;
	}
	public DelaRosaWeightConvertor(int lbs) { //non-default constructor
		this.lbs = lbs;
	}
	public int getPounds() { //accessor method
		return lbs;
	}
	public void addPounds(int adding) { //mutator method to add weight
		lbs += adding;
	}
	public double convertGrams() { //converts pounds variable to grams
		double grams = lbs*453.592;
		return grams;
	}
	public String toStringPound() { //convert pounds variable to a String
		return Integer.toString(lbs);
	}
	public String toStringGrams() { //convert ponds variable to grams in String form
		return Double.toString(convertGrams());
	}
}

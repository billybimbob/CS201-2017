package test2;

import java.util.*;

public class Average {

	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.println("This program averages all of the inputed numbers");
		System.out.println("Enter in a negative number to stop inputing numbers");

		ArrayList<Double> accumulate = new ArrayList<Double>();
		//double counter = 0;
		int count = -1;
		boolean negNum = false;
		do {
			try {
				System.out.print("Enter in a positive number: ");
				double inNum = Double.parseDouble(kboard.nextLine());
				if (inNum >= 0) {
					accumulate.add(inNum);
					//counter+=inNum;
					count++;
				} else
					negNum = true;
			} catch (Exception e) {
				System.out.println("Not a valid input");
			}
		} while (!negNum);

		double adding = 0;
		for (double i: accumulate) {
			adding+=i;
		}
		System.out.println("The average is " + adding/count);

		kboard.close();
	}
}

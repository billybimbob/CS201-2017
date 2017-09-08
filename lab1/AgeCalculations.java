package lab1;

import java.util.Scanner;

//Exercise #2
public class AgeCalculations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner(System.in);
		System.out.println("This program will calculate information about yourself\nBut first, we need some starting information\n");
		System.out.print("Please enter your age: ");
		int yourAge = kboard.nextInt();
		System.out.print("Please enter your father's age: ");
		int fatherAge = kboard.nextInt();
		System.out.print("Please enter your height in inches: ");
		int heightInches = kboard.nextInt();
		
		System.out.println("The age of your father minus your age is " + (fatherAge - yourAge));
		System.out.println("Your height in centimeters is " + (heightInches*2.54));
		System.out.println("Your height in feet and inches is " + (heightInches/12) + " feet and " + heightInches%12 + " inches");
	}

}

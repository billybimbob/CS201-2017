package lab1;

import java.util.Scanner;

//This program will convert inches to centimeters
//Assignment #3
public class InCmConverter {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	Scanner kboard = new Scanner(System.in);
	System.out.println("This program will convert inches to centimeters");
	System.out.print("Please enter in any height: ");
	double heightInches = kboard.nextInt();
	double heightCenti = heightInches*2.54; //Converts in to cm
	
	System.out.println("The height in centimeters is " + heightCenti);
	kboard.close();
	}

}

/*  Test Data(in)	|	Expected Data		|	Actual Result		|
	1		|		2.54		|		2.54		|
	12		|		30.48		|		30.48		|
	100		|		254		|		254.0		|
	25		|		63.5		|		63.5		|
*/
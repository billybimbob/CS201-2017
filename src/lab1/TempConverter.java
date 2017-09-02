package lab1;

import java.util.Scanner;

//This program converts F to C and vice-versa
//Assignment #1
public class TempConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This program is a temperature converter");
		Scanner kboard = new Scanner(System.in);
		System.out.print("Please enter a temperature in Fahrenheit: ");
		double fTemp = kboard.nextInt();
		System.out.print("Please enter a temperature in Celsius: ");
		double cTemp = kboard.nextInt();
		
		double fTempCon = (fTemp-32) * 5/9;
		double cTempCon = (cTemp * 9/5) + 32;
		System.out.println("The temperature of Fahrenheit converted to Celcius is " + fTempCon);
		System.out.println("The temperature of Celcius converted to Fahrenheit is " + cTempCon);
	}

}


/*  Test Condition	|		Test Data	|		Expected Data		|		Actual Result		|
	Low Temp, F	|		32		|		0		|		0		|
	Middle Temp, F	|		60		|		15.6		|		15.6		|
	High Temp, F	|		212		|		100		|		100		|
	Low Temp, C	|		0		|		32		|		32		|
	Middle Temp, C	|		50		|		122		|		122		|
	High Temp, C	|		100		|		212		|		212		|
*/
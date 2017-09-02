package lab1;

import java.util.Scanner;

//This program will calculate the surface area of a box with inputed dimensions\nPlease input all dimensions in inches
//Assignment #2
public class BoxCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This program will calculate the surface area of a box with inputed dimensions\nPlease input all dimensions in inches");
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter the length of the box: ");
		int len = kboard.nextInt();
		System.out.print("Enter the width of the box: ");
		int wid = kboard.nextInt();
		System.out.print("Enter the depth of the box: ");
		int dep = kboard.nextInt();
		
		int surfArea = 2*len*wid + 2*len*dep + 2*wid*len;
		System.out.println("The surface area of the box is " + surfArea);
	}

}

/*  Test Data(l,w,h)	|	Expected Data		|	Actual Result		|
	1,1,1		|		6		|		6		|
	2,3,4		|		40		|		40		|
	6,6,6		|		216		|		216		|
	0,0,0		|		0		|		0		|
*/
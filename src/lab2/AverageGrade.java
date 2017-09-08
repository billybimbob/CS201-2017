package lab2;

//Exercise 2
//This program will calculate the average of the a certain amount of inputed

import java.util.Scanner;
public class AverageGrade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This program will caculate the average grade\nEnter in -1 to finish inputing\n");
		Scanner kboard = new Scanner(System.in);
		double input = 0;
		double total = 0;
		int count = 0;
		
		do {
			System.out.print("Enter a grade: ");
			input = kboard.nextDouble();
			total += input; //adds the input to the accumulator
			count += 1; //counter to keep track of how many numbers are inputed
		} while(input != -1); //control to check if input does not equal -1
		
		double average = (total+1)/(count-1); //calculates the average
		//the loop is still executed when the input is -1, so the average calculation accounts for that
		System.out.println("The average of all the grades is " + average);
		kboard.close();
	}

}

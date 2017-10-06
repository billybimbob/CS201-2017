package lab3;

import java.io.*;
import java.util.Scanner;

public class GradeReader {

	public static void main(String[] args) {

		File inFile = new File("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\lab3\\grades.csv"); //the file location of grades.csv, dependent on the computer
		try {
			Scanner input = new Scanner(inFile);
			input.useDelimiter(","); //splits the lines into the name and the number
			double accumulate = 0;
			int count = 0;
			
			while (input.hasNextLine()) {
				input.next(); //accounts for the name string
				String num = input.next(); //the number string
				accumulate += Double.parseDouble(num.substring(0, num.indexOf("%"))); //looks for only the number string and parses it
				input.next(); //accounts for the \n
				count ++;
			}
			double average = accumulate/count; //determines the average
			System.out.println("The average of all of the grades is " + average);
			
			input.close();
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}

}

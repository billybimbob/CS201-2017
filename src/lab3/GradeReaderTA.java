package lab3;

import java.io.*;
import java.util.Scanner;

public class GradeReaderTA {

	public static void main(String[] args) {
	
		try {
			File inFile = new File("src\\lab3\\grades.csv"); //the file location of grades.csv, dependent on the computer
			Scanner input = new Scanner(inFile);
			input.useDelimiter(",|\\n"); // delimiters are "," OR "\n"
			double accumulate = 0;
			int count = 0;
			
			while (input.hasNextLine()) {
				input.next(); //accounts for the name string
				String num = input.next();//the number string
				accumulate += Double.parseDouble(num.substring(0, num.indexOf("%"))); //looks for only the number string and parses it
				
				//You do not need the following line since \n is also one of your delimiter
				//input.next(); //accounts for the \n  
				
				count ++;
			}
			double average = accumulate/count; //determines the average
			System.out.println("The average of all of the grades is " + average);
			
			input.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Something went wrong"); //ends the program if error
		}
	}

}


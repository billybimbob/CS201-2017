package lab3;

import java.util.Scanner;
import java.io.*;

public class NumberFile {

	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		String accumulate = "";
		
		while (true) { //is while true with a break so that the accumulator is skipped
			System.out.print("Enter in \"Done\" when finished\nEnter in a number: ");
			String input = kboard.nextLine();
			if (input.equals("Done") || input.equals("done"))
				break; // breaks out of the loop when "done" is entered
					   // accounts for "done" being lowercase
			try {
				Integer.parseInt(input);
				accumulate = accumulate + input + " "; //accumulator for all the numbers
			} catch (Exception e) {
				System.out.println("That is not a number, please try again");
			}
			System.out.println("");
		}
		
		System.out.print("\nEnter a name for the numbers to be\nWritten to a file: ");
		String name = kboard.nextLine() + ".txt"; //makes the file name a text file
		System.out.println("");
		try {
			FileWriter outFile = new FileWriter("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\lab3\\" + name); //sets the file location
			//file location specific to my computer, may have to change
			
			BufferedWriter output = new BufferedWriter(outFile);
	        output.write(accumulate); //writes accumulated string to file
	        
	        output.close();
	        System.out.println("Your numbers have been written to a text file");
		} catch (Exception e) {
			System.out.println("Something went wrong"); //not really sure when this would happen, program ends, and file isn't written
		}
		kboard.close();
	}

}

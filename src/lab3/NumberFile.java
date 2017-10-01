package lab3;

import java.util.Scanner;
import java.io.*;

public class NumberFile {

	public static void main(String[] args) throws IOException{
		Scanner kboard = new Scanner(System.in);
		String accumulate = "";
		
		while (true) { //is while true with a break so that the accumulator is skipped
			System.out.print("Enter in \"Done\" when finished\nEnter in a number: ");
			String input = kboard.nextLine(); //doesn't check if the string is a number
			if (input.equals("Done") || input.equals("done"))
				break; // breaks out of the loop when "done" is entered
					   // accounts for "done" being lowercase
			accumulate = accumulate + input + " "; //accumulator for all the numbers
			System.out.println("");
		}
		System.out.print("Enter a name for the numbers to be\nWritten to a file: ");
		String name = kboard.nextLine();
		System.out.println("");
		File outFile = new File(name);
		
		BufferedWriter output = new BufferedWriter(new FileWriter("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\lab3\\" + outFile));
        output.write(accumulate);
        
        output.close();
		kboard.close();
	}

}

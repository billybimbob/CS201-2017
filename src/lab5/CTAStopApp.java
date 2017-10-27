package lab5;

import java.io.*;
import java.util.Scanner;

public class CTAStopApp {

	public static void main(String[] args) {
		try {
			File inFile = new File("C:\\Users\\funte\\eclipse-workspace\\CS201\\src\\lab5\\ctaLineStops.csv");
			Scanner input = new Scanner(inFile);
			input.nextLine();
			input.useDelimiter(",|\\n");
			
			int count = 0;
			CTAStation[] greenLine = new CTAStation[29];
			
			while(input.hasNextLine()) {
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				input.next();
				boolean wheelChair = Boolean.parseBoolean(input.next());
				boolean opened = Boolean.parseBoolean(input.next());
				greenLine[count] = new CTAStation(inName, inLat, inLong, wheelChair, opened);
				input.next();
			}
			
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}

}

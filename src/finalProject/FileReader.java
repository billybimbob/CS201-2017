package finalProject;

import java.io.*;
import java.util.Scanner;

public class FileReader { //reads from inputed file

	public static void parseFile() {
		try {
			File inFile = new File("C:\\Users\\narwhal\\eclipse-workspace\\CS201\\src\\finalProject\\CTAStops.csv"); //file location varies based on computer
			Scanner input = new Scanner(inFile);
			
			input.nextLine(); //accounts for header
			input.nextLine();
			input.useDelimiter(",|\\n"); //divides by commas and \n
			
			//make each CTAline to store each station
			CTARoute[] lines = {
			new CTARoute("Blue Line"), new CTARoute("Brown Line"), new CTARoute("Green Line"), new CTARoute("Orange Line"),
			new CTARoute("Pink Line"), new CTARoute("Purple Line"), new CTARoute("Red Line"), new CTARoute("Yellow Line")};
			
			while(input.hasNextLine()) {
				//parsing csv file
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				String locCheck = input.next();
				Location inLocation = null;
				for (Location i: Location.values()) {
					if (locCheck.equals(i.toString()))
						inLocation = i;
				}
				if (inLocation==null)
					inLocation = null;
					
				boolean wheelChair = Boolean.parseBoolean(input.next());
				
				int
				redCheck = Integer.parseInt(input.next()), greenCheck = Integer.parseInt(input.next()),
				blueCheck = Integer.parseInt(input.next()), brownCheck = Integer.parseInt(input.next()),
				purpleCheck = Integer.parseInt(input.next()), pinkCheck = Integer.parseInt(input.next()),
				orangeCheck = Integer.parseInt(input.next()), yellowCheck = Integer.parseInt(input.next().trim()); //account for \n
				int[] indices = {blueCheck, brownCheck, greenCheck, orangeCheck, pinkCheck, purpleCheck, redCheck, yellowCheck};
				
				CTAStation station = new CTAStation(inName, inLat, inLong, inLocation, wheelChair, true,
						indices[0], indices[1], indices[2], indices[3], indices[4], indices[5], indices[6], indices[7]); //instantiate object with parsed parameters
				
				//both are if statements to account for stations that are multiple lines
				for (int i = 0; i < HandleData.lineColors.length; i++) { //add to each list if index is not -1
					if (indices[i] != -1)
						lines[i].addStation(station);
				}
			}
			for (int i = 0; i < HandleData.lineColors.length; i++) //sorts each list
				lines[i].sort(i);
					
			HandleData.system = new CTASystem(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5], lines[6], lines[7]); //not sure
			
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong when reading the file");
		}
	}
	
	public static void writeFile () { //bufferedwriter doesn't add \n
		try {
			FileWriter outFile = new FileWriter("CTA Train System.txt"); //set to the default file location
			
			BufferedWriter output = new BufferedWriter(outFile);
			output.write(HandleData.system.toString()); //writes accumulated string to file
			
			output.close();
			System.out.println("The CTA System has been written to a text file");
		} catch (Exception e) {
			System.out.println("Something went wrong, file cannot be written");
		}
	}
}

package lab5;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CTAStopApp {

	private static CTAStation[] greenLine;
	
	public static void main(String[] args)  {
		Scanner kboard = new Scanner(System.in);
		try {
			File inFile = new File("src\\lab5\\greenLineStops.csv"); //file location varies based on computer
			Scanner input = new Scanner(inFile);
			
			int count = 0;
			while(input.hasNextLine()) { //goes through file, and counts have many stations there are based on the number of lines
				input.nextLine();
				count++;
			}
			
			input.close(); //close and recreate file in order to reset the reader to the beginning
			input = new Scanner(inFile);
			
			input.nextLine(); //accounts for header
			input.useDelimiter(",|\\n"); //divides by commas and \n
			greenLine = new CTAStation[count-1]; //length determined from above
			count = 0; //counter reset
			
			while(input.hasNextLine()) {
				String inName = input.next();
				double inLat = Double.parseDouble(input.next()), inLong = Double.parseDouble(input.next());
				//System.out.println(inName + inLat + inLong);
				String inLocation = input.next();
				boolean wheelChair = Boolean.parseBoolean(input.next()), opened = Boolean.parseBoolean(input.next().trim()); //had to account for the \n on the last boolean, so used trim method
				//System.out.println(Boolean.toString(wheelChair)+Boolean.toString(opened));
				greenLine[count] = new CTAStation(inName, inLat, inLong, inLocation, wheelChair, opened, -1, -1); //create object based on parses of the current line
				count++;
			}
			
			System.out.println("Welcome to the CTA Green Line Information Center");
			userCommand:
			while(true) { //only breaks with the exit case in switch
				boolean haveAction = false;
				int choice = 0;
				System.out.println("\nPlease Select One of the Following Commands to Display Specific Information:");
				System.out.println("-----------------------------------------------");
				System.out.println("1. Display Station Names\n2. Display Stations with/without Wheelchair Access\n3. Display the Distance to Each Station\n4. Exit");
				System.out.println("-----------------------------------------------");
				do {
					try {
						System.out.print("Select which Number You want: ");
						choice = Integer.parseInt(kboard.nextLine());
					} catch (Exception e) {} //keeps program from crashing
					
					if ((choice <= 4 && choice > 0)) //Checks if input is valid
						haveAction = true;
					else
						System.out.println("\nInvalid Choice, Please Try Again\n");
				} while (!haveAction);
				
				System.out.println("");
				switch (choice) {
				case 1:
					displayStationNames();
					break;
				case 2:
					displayWheelchair(kboard);
					break;
				case 3:
					displayDistance(kboard);
					break;
				case 4:
					break userCommand;
				}
				TimeUnit.SECONDS.sleep(2); //waits 2 seconds between each display of info
			}
			
			input.close();
		} catch (Exception e) { //program should automatically end if exception thrown
			System.out.println("Something went wrong");
		}
		System.out.println("The CTA Information Center Has Closed");
		kboard.close();
	}
	
	//methods for each display of data
	public static void displayStationNames() { //goes through array and prints name
		System.out.println("All of the Stations on the CTA Green Line are: ");
		for (int i = 0; i < greenLine.length; i++) {
			System.out.println(greenLine[i].getName());
		}
	}
	public static void displayWheelchair(Scanner keyboard) { //display wheelchair accessible or non-wheelchair accessible stations
		boolean searchWheel = false, haveResponse = false;
		do {
			System.out.println("Do You Want to Show Stations with Wheelchair Accessibility?");
			System.out.print("Type in \'y\' for yes or \'n\' for no: ");
			char[] respStore = keyboard.nextLine().toCharArray();
			if (respStore.length == 1 && (respStore[0] == 'y' || respStore[0] == 'n')) { //checks if response is valid, looks for specifically y or n
				char response = respStore[0];
				searchWheel = response=='y';
				haveResponse = true;
			} else {
				System.out.println("Not a Valid Response\n");
			}
		} while (!haveResponse);
		
		int statFound = 0;
		String withOrOut;
		if (searchWheel) //change phrase based on response
			withOrOut = "with";
		else
			withOrOut = "without";
		System.out.println("\nHere are the Stations " + withOrOut + " Wheelchair Accessibility:");
		for (int i = 0; i < greenLine.length; i++) { //goes through each station and checks the wheelchair boolean
			if (greenLine[i].getWheelchair() == searchWheel) { //prints station name if true
				System.out.println(greenLine[i].getName());
				statFound++;
			}
		}
		if (statFound == 0)
			System.out.println("No Stations were Found");
	}
	public static void displayDistance(Scanner keyboard) { //displays nearby stations based on inputed location
		double curLat = 0, curLon = 0;
		boolean validLoc = false;
		do { //getting the location variables is kind of wonky as, the user needs to know precise location of where they are
			try {
				System.out.print("Enter your current latitude location: ");
				curLat = Double.parseDouble(keyboard.nextLine());
				System.out.print("Enter your current longitude location: ");
				curLon = keyboard.nextDouble();
				validLoc = true;
			} catch (Exception e) {
				System.out.println("Invalid Input\n");
			}
		} while (!validLoc); //breaks out of loops if the input is a double
		
		int nearLoc = 0;
		System.out.println("\nNearby stations are:");
		for (int i = 0; i < greenLine.length; i++) {
			if (greenLine[i].calcDistance(curLat, curLon) < 0.1) { //not sure what the threshold for nearby distance is
				System.out.println(greenLine[i].getName() + " - " + greenLine[i].calcDistance(curLat, curLon) + " degrees away");
				nearLoc++;
			}
		}
		if (nearLoc == 0) //determines if there are no stations within specified range
			System.out.println("There Seems to be no Nearby Stations");
	}

}

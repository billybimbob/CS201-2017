package finalProject;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CTAStopAppFinal { //class where main method is, foundation for ui

	public static Scanner keyboard; //public because referenced in other classes
	
	public static void main(String[] args)  {
		keyboard = new Scanner(System.in); //scanner for user input
		try {
			FileReader.parseFile(); //parses the file
			System.out.println("Welcome to the CTA Green Line Information Center");
			userCommand:
			while(true) { //only breaks with the exit case in switch
				boolean haveAction = false;
				int choice = 0;
				System.out.println("\n\nPlease Select One of the Following Commands to Display Specific Information:");
				System.out.println("-----------------------------------------------");
				System.out.println(""
						+ "1. Display Information for All CTA Stations\n"
						+ "2. Display Information for Specific Station\n"
						+ "3. Add New Station\n"
						+ "4. Modify Existing Station\n"
						+ "5. Delete a Station\n"
						+ "6. Create Route\n"
						+ "7. Display the Nearest Station\n"
						+ "8. Exit");
						
				System.out.println("-----------------------------------------------");
				do {
					try {
						System.out.print("Select which Number You want: ");
						choice = Integer.parseInt(keyboard.nextLine()); //user input
					} catch (Exception e) {} //keeps program from crashing
					
					if ((choice <= 8 && choice > 0)) //Checks if input is valid
						haveAction = true;
					else
						System.out.println("\nInvalid Choice, Please Try Again\n");
				} while (!haveAction);
				
				System.out.println("");
				switch (choice) { //performs method based on input
				case 1:
					HandleData.displayAll();
					break;
				case 2:
					HandleData.displaySpecific();
					break;
				case 3:
					HandleData.addStation();
					break;
				case 4:
					HandleData.modifyStation();
					break;
				case 5:
					HandleData.removeStation();
					break;
				case 6:
					HandleData.createRoute();
					break;
				case 7:
					HandleData.nearestStation();
					break;
				case 8: //need to write to text file
					FileReader.writeFile();
					break userCommand;
				}
				TimeUnit.SECONDS.sleep(2); //waits 2 seconds between each display of info
			}
			
		} catch (Exception e) { //program should automatically end if exception thrown
			e.printStackTrace();
			//System.out.println(e);
			System.out.println("Something went wrong");
		}
		System.out.println("The CTA Information Center Has Closed");
		keyboard.close();
	}
}
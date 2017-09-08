package lab2;

// Exercise 3
// This is a console that can add, multiply, or say hi, based on your choice

import java.util.Scanner;
public class ChoiceMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is a console that can add, multiply, or say hi, based on your choice");
		Scanner kboard = new Scanner(System.in);
		int input = 0;
		boolean control = true;
		while (control) {
			System.out.print("\n1. Say Hello\n2. Add 2 Numbers\n3. Multiply 2 Numbers\n4. Exit\nEnter in a number to run one of the functions: ");
			input = kboard.nextInt();
			System.out.println("");
			switch (input) {
				case 1: //say hello
					System.out.println("Hello!");
					break;
				case 2: //for the addition function
					System.out.print("Enter in a number: ");
					double add1 = kboard.nextDouble();
					System.out.print("Enter in another number: ");
					double add2 = kboard.nextDouble();
					System.out.print("The sum of the two numbers is " + (add1+add2)); //adds the two inputs
					break;
				case 3: // for the multiplication function
					System.out.print("Enter in a number: ");
					double mult1 = kboard.nextDouble();
					System.out.print("Enter in another number: ");
					double mult2 = kboard.nextDouble();
					System.out.print("The product of the two numbers is " + (mult1 * mult2)); //multiplies the two inputs
					break;
				case 4:
					control = false; //exits the while loop
					break;
				default:
					System.out.println("Invalid input, please actually read what is happening");
			}
		}
		System.out.println("The program has ended");
		kboard.close();
	}

}

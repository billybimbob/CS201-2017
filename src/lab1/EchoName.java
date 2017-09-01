package lab1;

import java.util.Scanner;
public class EchoName {

	//This program repeats the inputed name (complex I know)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = kboard.nextLine();
		char firstInitial = name.charAt(0);
		System.out.println("Your name is " + name);
		System.out.println("Your first initial is " + firstInitial);
	}

}

//package test;

import java.util.Scanner;

//Question 1
public class DataTypes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner (System.in);
		boolean check = true;
		int answer = 0;
		do {
			System.out.print("Enter a number from 1 to 10: ");
			int input = kboard.nextInt();
			if (input >= 1 && input <= 10) {
				answer = (input*2) + 65;
				check = false;
			} else {
				System.out.println("Your input is invalid");
			}
		} while (check);
		System.out.println((char)answer);
		kboard.close();
	}

}

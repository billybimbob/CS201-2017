//package test;

import java.util.Scanner;

//Question 2
public class Selection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner (System.in);
		System.out.print("Enter any integer: ");
		int input = kboard.nextInt();
		if (input % 3 == 0)
			System.out.print("foo");
		if (input % 4 == 0)
			System.out.print("bar");

		kboard.close();
	}

}

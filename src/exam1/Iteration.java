//package test;

import java.util.Scanner;

//question 3
public class Iteration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner (System.in);
		System.out.print("Enter in a number for the height of the triange: ");
		int height = kboard.nextInt();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < height-i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		kboard.close();
	}

}

//package test;

import java.util.Scanner;

//Question 4
public class Arrays {
	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		String[] array = new String[5];
		for (int i = 0; i < 5; i++) {
			System.out.print("Enter in a word: ");
			array[i] = kboard.nextLine();
		}
		String firstWord = array[0];
		for (int i = 0; i < array.length-1; i++) {
			if (array[i].compareTo(array[i+1]) > 0)
				firstWord = array[i+1];
		}
		System.out.println("The first word is " + firstWord);
		kboard.close();
	}

}

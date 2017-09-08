package lab2;

//Exercise 1
//This program prints out a triangle with the the given height

import java.util.Scanner;
public class TrianglePic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kboard = new Scanner(System.in);
		System.out.print("Please enter the height of the triangle: ");
		int count = 0;
		count = kboard.nextInt();
		String triangle = "";
		while (count != 0) {
			triangle = triangle + "*"; //with each instance, the amount of stars is increased by one
			System.out.println(triangle);
			count--; //decrements the counting variable, so that control expression can eventually be met
		}
		kboard.close();
	}

}

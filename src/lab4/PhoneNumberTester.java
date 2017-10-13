package lab4;

import java.util.Scanner;

public class PhoneNumberTester {

	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter in an area code");
		PhoneNumber defaultNum = new PhoneNumber();
		PhoneNumber inputNum = new PhoneNumber("415","845", "1234");
		
		System.out.println("The first (default) phone number is " + defaultNum.getAreaCode() + "-" + defaultNum.getfirstDigits() + "-" + defaultNum.getLastDigits());
		System.out.println("The second phone number is " + inputNum.getAreaCode() + "-" + inputNum.getfirstDigits() + "-" + inputNum.getLastDigits());
		kboard.close();
	}

}

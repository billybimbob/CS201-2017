package lab4;

public class PhoneNumber {
	
	private String areaCode, firstDigits, lastDigits;
	
	public PhoneNumber() { //default constructor
		areaCode = "800";
		firstDigits = "000";
		lastDigits = "0000";
	}
	public PhoneNumber(String areaCode, String firstDigits, String lastDigits) { //non-default constructor
		this.areaCode = areaCode;
		this.firstDigits = firstDigits;
		this.lastDigits = lastDigits;
	}
	//accessor methods
	public String getAreaCode() {
		return areaCode;
	}
	public String getfirstDigits() {
		return firstDigits;
	}
	public String getLastDigits() {
		return lastDigits;
	}
	public String toString() {
		return areaCode + "-" + firstDigits + "-" + lastDigits;
	}
	
	//mutator methods
	public void changeAreaCode(String newNum) {
		if (newNum.length() == 3 && checkNum(newNum))
			this.areaCode = newNum;
		else
			System.out.println("That is not a valid area code");
	}
	public void changeFirstDigits(String newNum) {
		if (newNum.length() == 3 && checkNum(newNum))
			this.areaCode = newNum;
		else
			System.out.println("That is not a valid number");
	}
	public void changeLastDigits(String newNum) {
		if (newNum.length() == 4 && checkNum(newNum))
			this.areaCode = newNum;
		else
			System.out.println("That is not a valid number");
	}
	
	//check if lengths are correct and all the characters are numbers
	public boolean checkAreaCode() {
		return areaCode.length() == 3 && checkNum(areaCode);
	}
	public boolean checkPhoneNum() {
		return (firstDigits + lastDigits).length() == 7 && checkNum(firstDigits + lastDigits);
	}
	
	//method to check if all characters of argument string are all numbers
	public static boolean checkNum (String checking) {
		String numbers = "0123456789";
		boolean tempCheck  = true;
		for (int i = 0; i <= checking.length()-1; i++) {
			tempCheck = numbers.contains(checking.substring(i, i+1));
			if (!tempCheck)
				break;
		}
		return tempCheck;
		
	}
}
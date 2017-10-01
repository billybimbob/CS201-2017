package lab3;

public class MinVal {

	public static void main(String[] args) {
		int[] numList = {72, 101, 108, 108, 111, 32, 101, 118, 101, 114, 121, 111, 110, 101, 33, 32, 76, 111, 111, 107, 32, 97, 116, 32, 116, 104, 101, 115, 101, 32, 99, 111, 111, 108, 32, 115, 121, 109, 98, 111, 108, 115, 58, 32, 63264, 32, 945, 32, 8747, 32, 8899, 32, 62421};
		
		int minVal = numList[0]; //initialize minimum value to the first index
		for (int i = 1; i < numList.length-1; i++) {
			if (numList[i] > numList[i+1]) //if the value is less, then the lesser value is set as the minVal
				minVal = numList[i+1];
		}
		System.out.println("The minimum value is " + minVal);
	}

}

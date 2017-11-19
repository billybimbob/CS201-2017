package lab7;

public class BubbleSort {

	public static void main(String[] args) {
		
		int[] intList = {10, 4, 7, 3, 8, 6, 1, 2, 5, 9};
		
		boolean swap;
		do {
			swap = false;
			for(int i=0; i < intList.length-1; i++) { //all smaller values will gradually swap to left
				if(intList[i] > intList[i+1]) { //swaps to make smaller value go to the left
					int store = intList[i];
					intList[i] = intList[i+1];
					intList[i+1] = store;
					swap = true;
				}
			}
		} while(swap); //loops back through list if a swap occurred
		
		for (int num: intList) {
			System.out.print(num + " ");
		}
	}

}

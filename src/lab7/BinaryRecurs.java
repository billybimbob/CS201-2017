package lab7;

import java.util.Scanner;

public class BinaryRecurs {

	public static void main(String[] args) {
		
		Scanner kboard = new Scanner(System.in);
		String[] nameList = {"c", "html", "java", "python", "ruby", "scala"};
		
		System.out.print("Enter in a word you want to serach: ");
		String searching = kboard.nextLine(); //searches under the assumption that list is sorted
		int start = 0, end = nameList.length, idx = -1;
		
		int searched = search(nameList, start, end, idx, searching);
		
		if (searched==-1)
			System.out.println("Your word cannot be found");
		else
			System.out.println("Word is at position " + searched);
		
		kboard.close();
		
	}
	
	public static int search(String[] list, int start, int end, int idx, String searching) {
		int middle = (end+start)/2;
			
		if(start!=end) {
			if(list[middle].compareToIgnoreCase(searching) > 0)
				idx = search(list, start, middle-1, idx, searching);
			
			else if(list[middle].compareToIgnoreCase(searching) < 0)
				idx = search(list, middle+1, end, idx, searching);
			
			else
				idx = middle;
		}

		return idx;
			
	}

}

package lab7;

import java.util.Scanner;

public class BinaryRecurs {

	public static void main(String[] args) {
		
		Scanner kboard = new Scanner(System.in);
		String[] nameList = {"c", "html", "java", "python", "ruby", "scala", "xapa"};
		
		System.out.print("Enter in a word you want to search: ");
		String searching = kboard.nextLine(); //searches under the assumption that list is sorted
		int start = 0, end = nameList.length; //max set to last index of list: prevent index out of bounds exception
		
		int searched = search(nameList, start, end, searching);
		
		if (searched==-1) //return value -1 by default, meaning nothing was found
			System.out.println("Your word cannot be found");
		else
			System.out.println("Word is at position " + searched);
		
		kboard.close();
		
	}
	
	public static int search(String[] list, int start, int end, String searching) {
		int middle = (end+start)/2; //finds middle point between end and start, rounds down-splits list in half
		//System.out.println(start + " " + end + " "+ list[middle]);
		
		//general cases
		if(end-start==0) //checks if checking length is one or less
			return -1;  //if one or less, checks word remaining, not great since check twice
		
		else if(list[middle].compareToIgnoreCase(searching) > 0) //goes to top half since searching word is alphabetically after
			return search(list, start, middle, searching);
		
		else if(list[middle].compareToIgnoreCase(searching) < 0)//goes to bottom half since searching word is alphabetically before
			return search(list, middle+1, end, searching);
		
		else //base case, if searching word matches
			return middle;

	}

}

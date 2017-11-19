package lab7;

public class InsertionSort {

	public static void main(String[] args) {

		String[] nameList = {"cat", "fat", "python", "ruby", "scala"};
		
		for (int i = 0; i < nameList.length-1; i++) {
			int j = i+1; //find where to insert this value in sorted section  
			while(j >= 1 && nameList[j].compareToIgnoreCase(nameList[j-1]) > 0) {
				String store = nameList[j]; //if not sorted, swap
				nameList[j] = nameList[j+1];
				nameList[j+1] = store;
				j-=1;
			} //breaks out of loop when at end of list or when sorted section encountered
			//breaking inserts j value essentially
		}
		
		for (String name: nameList) { //prints list
			System.out.println(name);
		}
	}

}

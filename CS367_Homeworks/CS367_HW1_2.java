
import java.util.ArrayList;
import java.util.Iterator;


public class CS367_HW1_2 {

	/**
	 * The method find find all matches in the list, and replace them by 
	 * another element. It also reports the number of matches
	 * 
	 * @param match - the things you want to find
	 * @param replacement - the thing you want to replace with
	 * @param myList - the list you want to search  
	 * @return the number of matchesin the list  
	 */
	public static <E> int replace(E match, E replacement, ArrayList<E> myList) {
		// throw an exception if anything is null
		if (match == null || replacement == null || myList == null)
			throw new IllegalArgumentException();
		// check is the list is empty
		if (myList.size() == 0)
			return 0;
		// otherwise, traverse through the list
		int count = 0;	// keeping track of replacements
		int index = 0;	// keeping track of current index
		// create an iterator
		Iterator <E> itr = myList.iterator();
		while(itr.hasNext()){
			index ++;	// advance the current position
			E temp = itr.next();
			// if a match is found
			if (temp.equals(match)){
				// advance the count
				count ++;	
				// replace it 
				myList.set(index - 1, replacement);
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("B");
		myList.add("C");
		myList.add("X");
		System.out.println("Before: " + myList);
		replace("B", "X", myList);
		System.out.println("After: " + myList);
		
	}

}

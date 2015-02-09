import java.util.ArrayList;
import java.util.Iterator;


public class CS367_HW1_1 {

	/**
	 * Given a list, this method swap two elements accorrding to the 
	 * input indices 
	 * 
	 * precondition 1: x and y are >= 0 and < mylist.size() 
	 * precondition 2: mylist is not null
	 * 
	 * @param x - the 1st index
	 * @param y - the 2nd index
	 * @param myList - a list of items 
	 */
	static void swap(int x, int y, ListADT<String> myList) {
		// save the element in the x positon 
		String s = myList.get(x);
		// add y th element to x position 
		myList.add(x, myList.get(y));
		// remove the element that originally at x position 
		myList.remove(x + 1);
		// add s element to y position 
		myList.add(y, s);
		// remove the element that originally at y position
		myList.remove(y + 1);
	}

	
	public static void main(String[] args) {
		ListADT<String> mylist = new <String> SimpleArrayList();
		mylist.add("A");
		mylist.add("B");
		mylist.add("C");
		mylist.add("D");
		mylist.add("E");
		System.out.println(mylist);
		swap(3, 1, mylist);
		System.out.println(mylist);

	}

}

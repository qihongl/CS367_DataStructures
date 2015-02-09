public class SimpleArrayList implements ListADT<Object> {

	// *** fields ***
	private Object[] items; // the items in the List
	private int numItems;   // the number of items in the List
	private static final int INITSIZE = 10;


	public SimpleArrayList() {
	    items = new Object[INITSIZE]; // reading version
	    numItems = 0;
	}


	public void add(Object item) {
		// if array is full, get new array of double size,
		// and copy items from old array to new array
		
		if (items.length == numItems) {
			expandArray();
		}
		// add new item; update numItems
		items[numItems] = item;
		numItems++;
	}


	private void expandArray() {
		Object[] newArray = new Object[numItems*2];
		for (int k = 0; k < numItems; k++) {
			newArray[k] = items[k];
		}
		items = newArray;
	}


	public void add(int pos, Object item) {
		// check for bad pos and for full array
		if (pos < 0 || pos > numItems) {
			throw new IndexOutOfBoundsException();
		}
		if (items.length == numItems) {
			expandArray();
		}
		// move items over and insert new item
		for (int k = numItems; k > pos; k--) {
			items[k] = items[k-1];
		}
		items[pos] = item;
		numItems++;
	}


	public boolean contains(Object item) {
		if (item == null)
			throw new IllegalArgumentException();
		for (int i = 0; i < numItems; i ++){
			if (items[i].equals(item)){
				return true;
			}
		}
		return false;
	}


	public Object get(int pos) {
		if(pos > items.length || pos < 0)
			throw new IndexOutOfBoundsException();
		return items[pos];
	}


	public boolean isEmpty() {
		return numItems == 0;
	}

	public Object remove(int pos) {
		if(pos > items.length || pos < 0)
			throw new IndexOutOfBoundsException();
		if (isEmpty())
			throw new NullPointerException();
		
		Object removedItem = items[pos];
		for (int i = pos; i < items.length - 1; i ++){
			items[i] = items[i+1]; 
		}
		numItems --;
		return removedItem;
	}


	public int size() {
		return numItems;
	}      


}  
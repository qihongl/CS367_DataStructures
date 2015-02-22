import java.util.Iterator;
import java.util.LinkedList;

public class DLinkedList<E> implements ListADT<E>, Iterable<E>{	
	// private field
	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;

	/**
	 * constructor
	 * */
	public DLinkedList(){
		head = null;
		tail = null;
		numItems = 0;
	}
	
	/**
	 * Add an item to the end 
	 * 
	 * @param item - the item that you want to add to the end 
	 * */
	@Override
	public void add(E item) {
		// TODO 
		// Input validation 
		if (item == null)
			throw new IllegalArgumentException();
		// create a new listnode with input data
		Listnode <E> newnode = new Listnode<E> (item);
		Listnode <E> cur;
		if (head == null){
			head = newnode;
		} else {
			cur = head;
			// go to the end of the listnodes
			while(cur.getNext() != null){
				cur = cur.getNext();
			}			
			// set the last one to be the newnode
			cur.setNext(newnode);
		}
		numItems ++;
	}
	
	/**
	 * Add an item to a particular position 
	 * 
	 * @param pos - the position where you want to add an item 
	 * @param item - the item that you want to add 
	 */
	@Override
	public void add(int pos, E item) {
		// TODO Auto-generated method stub
	    // check for bad position
	    if (pos < 0 || pos > numItems) {
	        throw new IndexOutOfBoundsException();
	    }
	    // if asked to add to end, let the other add method do the work
	    if (pos == numItems) {
	        add(item);
	    }
	    // find the node n after which to add a new node and add the new node
	    Listnode<E> cur = head;
	    for (int k = 0; k < pos; k++) {
	        cur = cur.getNext();
	    }
	    Listnode<E> newnode = new Listnode<E>(item);
	    newnode.setNext(cur.getNext());
	    cur.setNext(newnode);
	    numItems++;
	}

	
	/**
	 * Check if the list contains the input item 
	 * 
	 * @param item - the item you want to check
	 * @return true if the list contains the item, false otherwise 
	 */
	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		if (item == null) throw new IllegalArgumentException();
		Listnode <E> cur = head;
		while(cur.getNext() != null){
			cur = cur.getNext();
			if(cur.getData().equals(item)){
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Get an item on a particular position 
	 * 
	 * @param pos - the index of the item
	 */
	@Override
	public E get(int pos) {
		// TODO Auto-generated method stub
		// input validation 
		if(pos < 0 || pos >= numItems)
			throw new IndexOutOfBoundsException();
		Listnode<E> cur = head;
		// traverse to the position 
		for(int i = 0; i < pos; i ++){
			cur = cur.getNext();
		}
		return cur.getData();
	}

	/**
	 * Check if the list is empty 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (numItems == 0 )
			return true;
		else 
			return false;
	}

	/**
	 * Remove an item on a particular position 
	 * 
	 * @param pos - the position 
	 */
	@Override
	public E remove(int pos) {
		// TODO Auto-generated method stub
		if(pos < 0 || pos > numItems) throw new IllegalArgumentException();
		// go to the position 
		Listnode<E> cur = head;
		for(int i = 0; i < pos; i ++){
			cur = cur.getNext();
		}
		cur.setNext(cur.getNext().getNext());
		return null;	// TODO return the item removed 
	}


	/**
	 * Return the number of items in the list
	 * 
	 *  @return numItems 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numItems;
	}

	/**
	 * Get an iterator 
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new LinkedList().iterator();
	}

}

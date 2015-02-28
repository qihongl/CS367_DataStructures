import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Testing {
	/**
	 * print all nodes in the input listnodes
	 * */
	public static <E> void printListnodes (Listnode<E> head){

		// holding the current node
		Listnode<E> cur = head;
		// print the head
		System.out.print(head.getData());
		// print all the nodes in the list 
		while(cur.getNext() != null){
			cur = cur.getNext();
			System.out.print(" ---> " + cur.getData());
		}
		System.out.println();

	}

	/**
	 * initialize the listnodes with a item array
	 * */
	public static <E> void initListnodes(ArrayList<E>items, Listnode<E> head){
		Listnode<E> cur = head;
		for(E e : items){
			cur.setNext(new Listnode<E>(e));
			cur = cur.getNext();
		}
	}


	public static void main (String [] args) throws FileNotFoundException{
		DLinkedList<String> dlist = new DLinkedList<>();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("ab");
		arr.add("bc");
		arr.add("cd");
		arr.add("de");
		
		Listnode<String> head = new Listnode<String>();
		initListnodes(arr, head);
		printListnodes(head);


	}
}

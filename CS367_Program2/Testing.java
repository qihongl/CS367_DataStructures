import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Testing {
	

	public static void main (String [] args){
		DLinkedList<String> dlist = new DLinkedList<>();
		
		Listnode<String> head = new Listnode<String>(null);
		PrintStream ps = new PrintStream(System.out);
		ps.append("asd\n");
		ps.append("this is just a testing.");
		ps.println("");
		
		
	}
}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;


public class Testing {

	public static String forRemove (int pos, List<String> list){
		String temp = null;
		
		if (list.size() == 0){
			throw new EmptyListException();
		}
		
		try{
			temp = list.remove(pos);
		} catch (IndexOutOfBoundsException ex){
			if (pos > list.size() - 1){
				temp = list.remove(list.size() - 1);
			} else if (pos < 0 ){
				temp = list.remove(0);
			}
		} catch (Exception e) {
			System.out.println("Other exceptions");
		}
		return temp;
	}
	
	public static void printList (List<String> list){
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			String temp = itr.next();
			System.out.println(temp);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
//		list.add("0");
//		list.add("1");
//		list.add("2");
//		list.add("3");
		printList(list);
		
		
		System.out.println(forRemove(4 , list) + " is removed");
		printList(list);
		
	}

}

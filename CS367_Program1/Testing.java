import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Testing {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in); 
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		
		
		System.out.println(list);
		Iterator<String> itr = list.iterator();
		System.out.print(itr.hasNext());
		
	}

}

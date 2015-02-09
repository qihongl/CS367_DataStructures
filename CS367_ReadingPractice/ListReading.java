import java.util.ArrayList;


public class ListReading {

	/**
	 * This method duplicate every element in an array list 
	 * */
	public static <E> void duplicate(ArrayList<E> arr){
		for(int i = 0; i < 4; i ++){
			arr.add(i * 2, arr.get(i * 2));
		}
	}
	
	/**
	 * This method remove a item from the list  
	 * */
	public static <E> void removeItem (ArrayList<E> arr,  E item){
		for (int i = 0; i < arr.size(); i ++){
			if (arr.get(i) == item){
				arr.remove(i);
				i -- ;
			}
		}
	}
	
	public static void main(String[] args) {
		// Test yourself # 2
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("a");
		arr.add("b");
		arr.add("c");
		arr.add("d");
		System.out.println(arr);
		duplicate(arr);
		System.out.println(arr);
		removeItem(arr, "b");
		System.out.println(arr);
		
	}

}

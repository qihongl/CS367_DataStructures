
public class TryGeneric {
	
	/**
	 * This method use JAVA GENERIC type, so it can accept any type
	 * */
	public static <E> void printArray(E[] array){
		for (int i = 0; i < array.length; i ++){
			System.out.println(array[i] + " ");
		}
	} 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int len = 10;
		Double [] doubleArray = {1.1, 2.2, 3.3};
		String [] strArray = new String[len];
		for (int i = 0; i < len; i ++){
			strArray[i] = "i";
		}
		
		// it seems that generic doesn't works for primitive type
		printArray(doubleArray);
		printArray(strArray);
	}
	
}

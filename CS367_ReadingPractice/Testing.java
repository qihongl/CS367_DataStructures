public class Testing{
	

	public static void main(String [] args){
		SimpleArrayList list = new SimpleArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		for(int i = 0 ; i < 3; i ++){
			System.out.println(list.get(i));
		}
		
	}

}

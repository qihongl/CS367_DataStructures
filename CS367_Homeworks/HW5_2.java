
public class HW5_2 {


	public static void doSomething(int c, int d) {
		System.out.println(".");
		if (d > 0) doSomething(c+1, d-1);

		for (int i = 1; i <= d; i++) {
			System.out.print(i);
		}
		System.out.println(c);

		if (d > 0) doSomething(c+1, d-1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doSomething(3, 1);
	}

}

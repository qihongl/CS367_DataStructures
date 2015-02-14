
public class CS367_HW2_1 {


	private static boolean j1 = true;
	private static boolean j2 = false;
	private static boolean j3 = false;
	private static boolean j4 = false;
	
//	Consider the following pseudo-code:
		public static void main( ) throws Ex1  {
			
		    System.out.println("m begin");
		    try {
		        a();
		    } catch (Ex2 ex) {
		        System.out.println("m caught Ex2");
		    } catch (Ex3 ex) {
		        System.out.println("m caught Ex3");
		    } finally {
		        System.out.println("m finally");
		    }
		    System.out.println("m end");
		}

		static void a( ) throws Ex1, Ex3, Ex2 {
		    System.out.println("a begin");
		    b();
		    try {
		        if (j2) throw new Ex1();
		        c();
		    } catch (Ex2 ex) {
		        System.out.println("a caught Ex2");
		        throw new Ex3();
		    } catch (Ex3 ex) {
		        System.out.println("a caught Ex3");
		    } finally {
		        System.out.println("a finally");
		    }
		    System.out.println("a end");
		}

		static void b( ) throws Ex2 {
		    System.out.println("b begin");
		    try {
		        if (j1) throw new Ex2();
		        if (j3) throw new Ex3();
		    } catch (Ex3 ex) {
		        System.out.println("b caught Ex3");
		    }
		    System.out.println("b end");
		}

		static void c( ) throws Ex2 {
		    System.out.println("c begin");
		    if (j4) throw new Ex2();
		    System.out.println("c end");
		}
//		For the each part below determine the complete output that would be 
//		displayed if the pseudo-code above was run with the values of the q 
//		variables as specified below. Assume the exception classes Ex1, Ex2, 
//		and Ex3 each extend RuntimeException. If an exception is passed out 
//		of main, show the output of the runtime environment as "Program "
//				+ "terminated due to Exception ExN", where N is the 
//				particular exception number.
//
//1. What would be output if j1 is true and the other variables are false?
//2. What would be output if j2 is true and the other variables are false?
//3. What would be output if j3 is true and the other variables are false?
//4. What would be output if j4 is true and the other variables are false?
//5. How would the code need to be modified if exception type Ex1 were 
//a checked exception? Show only lines of code that with changes.
//	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

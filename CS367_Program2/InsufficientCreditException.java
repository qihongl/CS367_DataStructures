
public class InsufficientCreditException extends Exception {

	public InsufficientCreditException (String productName) {
		System.out.println("Insufficient funds for "+ productName);
	}

}

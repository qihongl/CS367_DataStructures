
public class InsufficientCreditException extends Exception {

//	public InsufficientCreditException () {
//		System.out.println("Insufficient funds for <PRODUCT-NAME>");
//	}

	public InsufficientCreditException (String message) {
//		super(message);
		System.out.println("Insufficient funds for <"+ message +">");
	}
//
//	public InsufficientCreditException(String message,Throwable throwable){
//		super(message, throwable);
//		System.out.println("Insufficient funds for <PRODUCT-NAME>");
//	}
}

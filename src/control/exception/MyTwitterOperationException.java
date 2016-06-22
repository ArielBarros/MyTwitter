package control.exception;

public class MyTwitterOperationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Exception cause;
	private String message;
	
	public MyTwitterOperationException(String message) {
		this.message = message;
	}

	public MyTwitterOperationException(Exception cause) {
		super("Transaction not perfermed!");
		this.cause = cause;
	}

	public String getMessage() {
		String text = "Transaction not perfermed!\nCause: ";
		if (cause != null) {
			text += cause.getMessage();
		} else {
			text += message;
		}
		return text;
	}

	public Exception getCause() {
		return cause;
	}
}

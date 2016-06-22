package persistence.exception;

public class PIException extends PersistenceException {
	
	private static final long serialVersionUID = 1L;

	public PIException(String message, String number) {
		super(message, number);
	}
}

package persistence.exception;


public class UJCException extends PersistenceException {

	private static final long serialVersionUID = 1L;
	
	public UJCException(String message, String number) {
		super(message, number);
	}
}

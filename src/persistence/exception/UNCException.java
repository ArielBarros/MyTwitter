package persistence.exception;

public class UNCException extends PersistenceException {
	private static final long serialVersionUID = 1L;
	
	public UNCException(String message, String number) {
		super(message, number);
	}
}

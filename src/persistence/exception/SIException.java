package persistence.exception;

public class SIException extends PersistenceException {

	private static final long serialVersionUID = 1L;
	
	public SIException(String message, String number) {
		super(message, number);
	}

}

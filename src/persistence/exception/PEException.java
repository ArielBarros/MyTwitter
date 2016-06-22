package persistence.exception;

public class PEException extends PersistenceException {

	private static final long serialVersionUID = 1L;
	
	public PEException(String message, String number) {
		super(message, number);
	}
}

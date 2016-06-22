package persistence.exception;

public class MFPException extends PersistenceException {
	
	private static final long serialVersionUID = 1L;

	public MFPException(String message, String number) {
		super(message, number);
	}
	
}

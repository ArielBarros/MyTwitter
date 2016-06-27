package profile.exception;

import persistence.exception.PersistenceException;

public class MFPException extends PersistenceException {
	
	private static final long serialVersionUID = 1L;

	public MFPException(String message, String number) {
		super(message, number);
	}
	
}

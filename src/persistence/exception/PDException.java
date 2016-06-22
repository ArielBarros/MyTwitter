package persistence.exception;
public class PDException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public PDException(String message, String number) {
		super(message, number);
	}
}

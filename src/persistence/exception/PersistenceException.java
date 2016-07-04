package persistence.exception;

public class PersistenceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	protected String profile;
	protected String message;
	
	public PersistenceException(String message) {
		super("Persistence Exception!");
		this.message = message;
	}
	
	public PersistenceException(String message, String profile) {
		super("Persistence Exception!");
		this.profile = profile;
		this.message = message;
	}

	public String getMessage() {
		return this.message + " [Profile = " + profile + "]";
	}

	public String getProfile() {
		return profile;
	}
}

package profile.exception;

public class ProfileException extends Exception {
	private static final long serialVersionUID = 1L;
	
	protected String profile;
	protected String message;
	
	public ProfileException(String message) {
		super("Profile Exception!");
		this.message = message;
	}
	
	public ProfileException(String message, String profile) {
		super("Profile Exception!");
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

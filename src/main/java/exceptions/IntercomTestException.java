package exceptions;

public class IntercomTestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntercomTestException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}

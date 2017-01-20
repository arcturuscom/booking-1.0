package web.exceptions;

public class SessionExpiredException extends Throwable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionExpiredException(String message) {
		super(message);
	}

	public SessionExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionExpiredException(Throwable cause) {
		super(cause);
	}

}

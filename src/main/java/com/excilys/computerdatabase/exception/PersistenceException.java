package com.excilys.computerdatabase.exception;

/**
 * @author excilys
 *
 */
public class PersistenceException extends RuntimeException {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiate a new PersistenceException
	 */
	public PersistenceException() {
		super();
	}
	/**
	 * Instantiate new PersistenceException
	 * @param message String to display
	 * @param cause Error 
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Instantiate new PersistenceException
	 * @param message
	 */
	public PersistenceException(String message) {
		super(message);
	}
	/**
	 * Instantiate new PersistenceException
	 * @param message
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

}

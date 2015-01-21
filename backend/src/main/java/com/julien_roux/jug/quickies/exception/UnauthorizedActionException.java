package com.julien_roux.jug.quickies.exception;

public class UnauthorizedActionException extends Exception {

	private static final long serialVersionUID = -2912806211147781014L;

	public UnauthorizedActionException() {
	    super();
    }

	public UnauthorizedActionException(String message, Throwable cause) {
	    super(message, cause);
    }

	public UnauthorizedActionException(String message) {
	    super(message);
    }

	public UnauthorizedActionException(Throwable cause) {
	    super(cause);
    }
	
}

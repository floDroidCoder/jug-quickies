package com.julien_roux.jug.quickies.exception;

public class UnauthorizedActionException extends Exception {

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

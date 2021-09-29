package br.com.titcs.exceptions;

public class CustomRuntimeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3294731001017616811L;

	public CustomRuntimeException(String message) {
		super(message);
	}	
}

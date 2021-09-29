package br.com.titcs.exceptions;

public class UnauthorizedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5828020437912071L;

	public UnauthorizedException(String message) {
		super(message);
	}
	
	public UnauthorizedException() {
		super("Token inválido.");
	}
}

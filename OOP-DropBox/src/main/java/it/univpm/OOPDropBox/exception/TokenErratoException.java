package it.univpm.OOPDropBox.exception;

import java.io.IOException;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class TokenErratoException extends IOException{

	private static final long serialVersionUID = 5L;

	public TokenErratoException() {
		super();
	}

	public TokenErratoException(String message) {
		super(message);
	}
}
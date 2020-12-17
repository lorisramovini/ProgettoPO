package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class JsonErratoException extends RuntimeException{

	private static final long serialVersionUID = 3L;

	public JsonErratoException() {
		super();
	}

	public JsonErratoException(String message) {
		super(message);
	}
}

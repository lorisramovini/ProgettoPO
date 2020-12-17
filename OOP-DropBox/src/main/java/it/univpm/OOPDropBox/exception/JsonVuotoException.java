package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class JsonVuotoException extends RuntimeException{
	
	private static final long serialVersionUID = 6L;

	public JsonVuotoException() {
		super();
	}

	public JsonVuotoException(String message) {
		super(message);
	}

}

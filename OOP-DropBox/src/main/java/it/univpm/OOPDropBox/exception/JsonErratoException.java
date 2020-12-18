package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Eccezione che viene lanciata nel momento in cui nel body
 * le chiavi del JSON non rispecchiano quelle richieste
 */
public class JsonErratoException extends Exception{

	private static final long serialVersionUID = 3L;

	public JsonErratoException() {
		super();
	}

	public JsonErratoException(String message) {
		super(message);
	}
}

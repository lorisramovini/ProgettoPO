package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Eccezione lanciata nel momento in cui il body è formattato in JSON 
 * ma questo JSON è vuoto o manca di una delle due chiavi
 */
public class JsonVuotoException extends Exception{
	
	private static final long serialVersionUID = 6L;

	public JsonVuotoException() {
		super();
	}

	public JsonVuotoException(String message) {
		super(message);
	}

}

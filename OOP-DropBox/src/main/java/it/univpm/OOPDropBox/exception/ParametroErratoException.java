package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Eccezione lanciata nel momento in cui alla chiave Parametro del body
 * non viene assegnato un tipo tra quelli disponibili
 */
public class ParametroErratoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParametroErratoException() {
		super();
	}

	public ParametroErratoException(String message) {
		super(message);
	}
}

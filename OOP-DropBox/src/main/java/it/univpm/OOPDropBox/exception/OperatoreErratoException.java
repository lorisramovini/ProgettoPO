package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Eccezione lanciata nel momento in cui alla chiave Operatore nel body 
 * non sono stati assegnati dei valori tra quelli disponibili
 */
public class OperatoreErratoException extends Exception {
	private static final long serialVersionUID = 4L;

	public OperatoreErratoException() {
		super();
	}

	public OperatoreErratoException(String message) {
		super(message);
	}
}

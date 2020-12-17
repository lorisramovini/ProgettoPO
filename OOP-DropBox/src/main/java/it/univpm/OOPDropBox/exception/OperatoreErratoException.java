package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class OperatoreErratoException extends RuntimeException {
	private static final long serialVersionUID = 4L;

	public OperatoreErratoException() {
		super();
	}

	public OperatoreErratoException(String message) {
		super(message);
	}
}

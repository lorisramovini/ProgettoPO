package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class ParametroErratoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParametroErratoException() {
		super();
	}

	public ParametroErratoException(String message) {
		super(message);
	}
}

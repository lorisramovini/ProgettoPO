package it.univpm.OOPDropBox.exception;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Eccezione lanciata nel momento in cui il body non è scritto
 * in formato JSON
 */
public class FormatoJsonErratoException extends Exception{

		private static final long serialVersionUID = 2L;

		public FormatoJsonErratoException() {
			super();
		}

		public FormatoJsonErratoException(String message) {
			super(message);
		}

	}


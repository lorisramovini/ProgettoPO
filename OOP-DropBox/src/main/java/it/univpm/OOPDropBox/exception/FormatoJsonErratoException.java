package it.univpm.OOPDropBox.exception;

import org.json.simple.parser.ParseException;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class FormatoJsonErratoException extends RuntimeException{

		private static final long serialVersionUID = 2L;

		public FormatoJsonErratoException() {
			super();
		}

		public FormatoJsonErratoException(String message) {
			super(message);
		}

	}

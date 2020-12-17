package it.univpm.OOPDropBox.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class ExceptionModel {
	private final HttpStatus httpStatus;
	private final Instant instant;
	private final String errorName;
	private final String message ;
	
	public ExceptionModel(HttpStatus httpStatus, Instant instant, String errorName, String message) {

		this.httpStatus = httpStatus;
		this.instant = instant;
		this.errorName = errorName;
		this.message = message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public Instant getInstant() {
		return instant;
	}
	public String getErrorName() {
		return errorName;
	}
	public String getMessage() {
		return message;
	}
}

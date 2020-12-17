package it.univpm.OOPDropBox.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(value = { FormatoJsonErratoException.class })
	public ResponseEntity<Object> handleJsonErratoException(FormatoJsonErratoException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, Instant.now(),
				e.getClass().getCanonicalName(), e.getMessage());
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { JsonErratoException.class })
	public ResponseEntity<Object> handleJsonErratoException(JsonErratoException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, Instant.now(),
				e.getClass().getCanonicalName(), e.getMessage());
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { OperatoreErratoException.class })
	public ResponseEntity<Object> handleJsonErratoException(OperatoreErratoException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, Instant.now(),
				e.getClass().getCanonicalName(), e.getMessage());
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleRequestBodyException(HttpMessageNotReadableException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.BAD_REQUEST, Instant.now(),
				e.getClass().getCanonicalName(), "Il body non può essere vuoto");
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { TokenErratoException.class })
	public ResponseEntity<Object> handleTokenErratoException(TokenErratoException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.UNAUTHORIZED, Instant.now(),
				e.getClass().getCanonicalName(), e.getMessage());
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { ParametroErratoException.class })
	public ResponseEntity<Object> handleParametroErratoException(ParametroErratoException e) {

		// 1.Crea l oggetto errore model
		ExceptionModel errorModel = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, Instant.now(),
				e.getClass().getCanonicalName(), e.getMessage());
		// 2.return response entity
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

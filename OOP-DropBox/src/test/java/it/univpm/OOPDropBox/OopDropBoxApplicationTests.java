package it.univpm.OOPDropBox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.univpm.OOPDropBox.exception.FormatoJsonErratoException;
import it.univpm.OOPDropBox.exception.JsonErratoException;
import it.univpm.OOPDropBox.exception.JsonVuotoException;
import it.univpm.OOPDropBox.exception.OperatoreErratoException;
import it.univpm.OOPDropBox.exception.ParametroErratoException;
import it.univpm.OOPDropBox.exception.TokenErratoException;
import it.univpm.OOPDropBox.controller.MainController;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * @author Lorenzo Piccioni, Loris Ramovini
 * Classe con cui vengono effettuati i test
 */
@SpringBootTest
class OopDropBoxApplicationTests {
	private MainController mainController /* = new MainController() */;

	@BeforeEach
	void setUp() throws Exception {
		mainController = new MainController();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Verifichiamo che se il token inserito nella richiesta GET è errato
	 * venga lanciata l'eccezione TokenErratoException che segnali
	 * all'utente la necessità di inserire un token valido
	 */
	@Test
	void test() {

		assertThrows(TokenErratoException.class, () -> {
			mainController.Aggiornamento("");
		});

	}
	
	/**
	 * Verifichiamo che se il token inserito è valido, non viene 
	 * lanciata alcuna eccezione
	 */
	@Test
	void test1() {

		assertThrows(TokenErratoException.class, () -> {
			mainController.Aggiornamento("SmPZEJSz20wAAAAAAAAAAcf9JT138GPJCgA9vJI2h9fWt0vveDQiMyE7Qm6D3rXO");
		});

	}

	/**
	 * Verifichiamo su entrambe le richieste di tipo POST che 
	 * vengano lanciate tutte quante le varie eccezioni riguardanti
	 * un body non corretto
	 * 
	 * la prima operazione verifica che venga lanciata l'eccezione personalizzata
	 * FormatoJsonErratoException qualora il body non sia scritto in formato JSON
	 * 
	 * la seconda operazione verifica che venga lanciata l'eccezione JsonErratoException
	 * nel momento in cui il body inserito non abbia le chiavi corrette
	 * 
	 * la terza operazione verifica che venga lanciata l'eccezione OperatoreErratoException
	 * se il valore assunto dalla chiave operatore non è tra quelli previsti
	 * 
	 * la quarta operazione verifica che venga lanciata l'eccezione ParametroErratoException
	 * quando il valore assegnato alla chiave parametro non è tra i tipi presenti
	 */
	@Test
	void test2() {

		String bodyFormatoErrato = "";

		assertThrows(FormatoJsonErratoException.class, () -> {
			mainController.stats(bodyFormatoErrato);
		});
		assertThrows(FormatoJsonErratoException.class, () -> {
			mainController.statsMembri(bodyFormatoErrato);
		});

		String badJSON = " {\n" + "    \"provaerrato\": \"$eq\",\n"
				+ "    \"parametro\": \"photo\"\n" + "} ";

		assertThrows(JsonErratoException.class, () -> {
			mainController.stats(badJSON);
		});
		assertThrows(JsonErratoException.class, () -> {
			mainController.statsMembri(badJSON);
		});

		String bodyOperErrato = " {\n" + "    \"operatore\": \"provaerrato\",\n"
				+ "    \"parametro\": \"photo\"\n" + "} ";

		assertThrows(OperatoreErratoException.class, () -> {
			mainController.stats(bodyOperErrato);
		});
		assertThrows(OperatoreErratoException.class, () -> {
			mainController.statsMembri(bodyOperErrato);
		});

		String bodyParamErrato = " {\n" + "    \"operatore\": \"$eq\",\n"
				+ "    \"parametro\": \"provaerrato\"\n" + "} ";

		assertThrows(ParametroErratoException.class, () -> {
			mainController.stats(bodyParamErrato);
		});
		assertThrows(ParametroErratoException.class, () -> {
			mainController.statsMembri(bodyParamErrato);
		});
		
		String bodyJsonVuoto = "{}";

		assertThrows(JsonVuotoException.class, () -> {
			mainController.stats(bodyJsonVuoto);
		});
		assertThrows(JsonVuotoException.class, () -> {
			mainController.statsMembri(bodyJsonVuoto);
			});

	}

	/**
	 * Verifichiamo che in presenza di un body ben formato non venga lanciato nessun
	 * tipo di eccezione
	 */
	@Test
	void test3() {
		String body = " {\n" + "    \"operatore\": \"$eq\",\n"
				+ "    \"parametro\": \"photo\"\n" + "} ";

		assertThrows(Exception.class, () -> {
			mainController.stats(body);
		});
		assertThrows(Exception.class, () -> {
			mainController.statsMembri(body);
		});

	}
	

}

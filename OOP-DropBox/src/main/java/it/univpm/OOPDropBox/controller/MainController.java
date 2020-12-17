package it.univpm.OOPDropBox.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import it.univpm.OOPDropBox.exception.FormatoJsonErratoException;
import it.univpm.OOPDropBox.exception.JsonErratoException;
import it.univpm.OOPDropBox.exception.JsonVuotoException;
import it.univpm.OOPDropBox.exception.OperatoreErratoException;
import it.univpm.OOPDropBox.exception.TokenErratoException;
import it.univpm.OOPDropBox.filter.Filter;
import it.univpm.OOPDropBox.filter.Filtro;
import it.univpm.OOPDropBox.model.Link;
import it.univpm.OOPDropBox.model.StatCartella;
import it.univpm.OOPDropBox.model.StatUtenti;
import it.univpm.OOPDropBox.utils.SalvaDati;

/**
 * Controller dell'applicazione che implementa 6 metodi per gestire le varie richieste
 * 
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
@RestController
public class MainController {

	/**
	 * path che restituisce la lista di tutti gli shared link del profilo associato
	 * 
	 * @return ArrayList contenente tutti gli shared link
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/data", produces = "application/json")
	public ArrayList<Link> data() {
		return SalvaDati.getLink();
	}
	
	/**
	 * path che, se chiamato senza inserire alcun parametro,
	 * aggiorna la lista degli shared link qualora siano stati modificati
	 * nel profilo che si sta esaminando.
	 * Altrimenti se il token inserito è valido crea una nuova lista di shared links
	 * 
	 * @param token rappresenta il token del nuovo profilo Dropbox al quale si vuole fare riferimento
	 * per creare una nuova lista di shared links
	 * @return String per comunicare all'utente se l'operazione è andata a buon fine 
	 * @throws TokenErratoException
	 */
	@RequestMapping(method = RequestMethod.GET, value ={"/refresh","/refresh/{token}"}, produces = "application/json")
	public String Aggiornamento(@PathVariable(required=false) String token) throws TokenErratoException {
		String Risultato = "Database aggiornato";
		if(!(token==null)) {
			SalvaDati.setToken(token);
			SalvaDati.SalvaLink(SalvaDati.SalvaApi("https://api.dropboxapi.com/2/sharing/list_shared_links","{}"));
			return Risultato = "Nuovo database disponibile";
		}
		SalvaDati.SalvaLink(SalvaDati.SalvaApi("https://api.dropboxapi.com/2/sharing/list_shared_links","{}"));
		return Risultato;
	}

	/**
	 * path che fornisce la lista dei file per ogni cartella, indicando
	 * numero di link per cartella, quanti di questi sono sottocartelle 
	 * e quanti di questi sono file.
	 * Dopodiché mostra un vettore che indica il numero di file per ogni tipo
	 * 
	 * @return String contenente le informazioni richieste
	 * @throws JsonProcessingException
	 * @throws TokenErratoException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/stats", produces = "application/json")
	public String stats() throws JsonProcessingException, TokenErratoException {
		ArrayList<StatCartella> listaTutte = Filter.Stats();

		ObjectMapper mapper = new ObjectMapper();
		String Risultato = "";
		Risultato += mapper.writeValueAsString(listaTutte);
		return Risultato;

	}

	/**
	 * path che restituisce una lista quella della chiamata GET /stats ma filtrata
	 * in base al tipo di file richiesto nel body
	 * 
	 * @param body in formato JSON con operatore e tipologia da filtrare
	 * @return Stringa contenente le informazioni richieste
	 * @throws JsonProcessingException
	 * @throws TokenErratoException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/stats", produces = "application/json")
	public String stats(@RequestBody String body) throws JsonProcessingException, TokenErratoException {

		ArrayList<StatCartella> listaTutte = Filter.Stats();
		ObjectMapper mapper = new ObjectMapper();
		String Risultato = "";
		Filtro filter = new Filtro();

		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(body);
			ObjectMapper map = new ObjectMapper();
			filter = map.readValue(obj.toString(), Filtro.class);
		} catch (ParseException e) {
			throw new FormatoJsonErratoException("Inserisci un body in formato JSON");
		} catch (UnrecognizedPropertyException e) {
			throw new JsonErratoException("Body scritto in modo errato");
		}
		if(filter.getOperatore()==null) {
			throw new JsonVuotoException("Inserire delle coppie chiave valore nel JSON");
		}
		if (!(filter.getOperatore().contentEquals("$eq") || filter.getOperatore().contentEquals("$not"))) {
			throw new OperatoreErratoException("L'operatore può essere solo $eq oppure $not");
		}

		Filter.Selezione(listaTutte, filter);
		Risultato += mapper.writeValueAsString(listaTutte);
		return Risultato;
	}

	/**
	 * path che fornisce la lista dei file per ogni utente, indicando
	 * per ognuno di essi il numero di file che condivide con il profilo considerato
	 * e la loro dimensione media
	 * Dopodiché mostra un vettore che indica il numero di file per ogni tipo
	 * 
	 * @return Stringa contenente le informazioni richieste
	 * @throws JsonProcessingException
	 * @throws TokenErratoException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/statsMembri", produces = "application/json")
	public String statsMembri() throws JsonProcessingException, TokenErratoException {

		StatUtenti tot = Filter.StatMembri(SalvaDati.getLink());
		ObjectMapper mapper = new ObjectMapper();
		String Risultato = "";
		Risultato += mapper.writeValueAsString(tot);
		return Risultato;
	}

	/**
	 * path che restituisce una lista quella della chiamata GET /statsMembri ma filtrata
	 * in base al tipo di file richiesto nel body
	 * 
	 * @param body in formato JSON con operatore e tipologia da filtrare
	 * @return Stringa contenente le informazioni richieste
	 * @throws JsonProcessingException
	 * @throws TokenErratoException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/statsMembri", produces = "application/json")
	public String statsMembri(@RequestBody String body) throws JsonProcessingException, TokenErratoException {

		StatUtenti tot = Filter.StatMembri(SalvaDati.getLink());
		Filtro filter = new Filtro();
		ObjectMapper mapper = new ObjectMapper();
		String Risultato = "";

		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(body);
			ObjectMapper map = new ObjectMapper();
			filter = map.readValue(obj.toString(), Filtro.class);
		} catch (ParseException e) {
			throw new FormatoJsonErratoException("Inserisci un body in formato JSON");
		} catch (UnrecognizedPropertyException e) {
			throw new JsonErratoException("Body scritto in modo errato");
		}
		if(filter.getOperatore()==null) {
			throw new JsonVuotoException("Inserire delle coppie chiave valore nel JSON");
		}

		if (!(filter.getOperatore().contentEquals("$eq") || filter.getOperatore().contentEquals("$not"))) {
			throw new OperatoreErratoException("L'operatore può essere solo $eq oppure $not");
		}
		tot = Filter.SelezionePerMembro(filter);
	
		Risultato += mapper.writeValueAsString(tot);
		return Risultato;

	}
}

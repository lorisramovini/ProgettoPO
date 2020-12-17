package it.univpm.OOPDropBox.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.OOPDropBox.exception.TokenErratoException;
import it.univpm.OOPDropBox.model.File;
import it.univpm.OOPDropBox.model.Link;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class SalvaDati {

	private static ArrayList<Link> listaLink = new ArrayList<Link>();

	private static String Token = new String();

	/**
	 * @return
	 */
	public static String getToken() {
		return Token;
	}

	/**
	 * @param token
	 */
	public static void setToken(String token) {
		Token = token;
	}

	/**
	 * @return
	 */
	public static ArrayList<Link> getLink() {
		return listaLink;
	}

	/**
	 * @param listaLink
	 */
	public static void setListaLink(ArrayList<Link> listaLink) {
		SalvaDati.listaLink = listaLink;
	}

	public static JSONObject SalvaApi(String url, String jsonBody) throws TokenErratoException {
		JSONObject obj = new JSONObject();
		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization", "Bearer " + getToken());
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);

			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				in.close();
			}
			obj = (JSONObject) JSONValue.parseWithException(data);

		} catch (IOException e) {
			throw new TokenErratoException("token non valido, inserire un token valido per proseguire");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param obj
	 */
	public static void SalvaLink(JSONObject obj) {
		JSONArray arr = (JSONArray) obj.get("links");
		ArrayList<Link> link = new ArrayList<Link>();
		for (int i = 0; i < arr.size(); i++) {

			Link lista = new Link();
			JSONObject object = (JSONObject) arr.get(i);
			String tag = (String) object.get(".tag");
			String url = (String) object.get("url");
			String id = (String) object.get("id");
			String name = (String) object.get("name");
			String path = (String) object.get("path_lower");
			lista.setTag(tag);
			lista.setUrl(url);
			lista.setId(id);
			lista.setNome(name);
			lista.setPercorso(path);

			String x = "file";
			if (tag.contentEquals(x)) {
				String type = (String) object.get("preview_type");
				Long size = (Long) object.get("size");
				lista.setTipo(type);
				lista.setDimensione(size);
			}
			link.add(lista);
		}
		setListaLink(link);
	}

	/**
	 * @param vettore
	 * @param listaRisultato
	 * @return
	 */
	public static ArrayList<File> SalvaPerCartella(JSONArray vettore, ArrayList<File> listaRisultato) {
		for (int i = 0; i < vettore.size(); i++) {

			File lista = new File();
			JSONObject obj = (JSONObject) vettore.get(i);
			String id = (String) obj.get("id");
			String tag = (String) obj.get(".tag");

			lista.setId(id);
			lista.setTag(tag);

			if (tag.contentEquals("file")) {
				for (Link r : SalvaDati.getLink()) {
					if (id.contentEquals(r.getId())) {
						lista.setTipo(r.getTipo());
					}
				}
			}
			listaRisultato.add(lista);
		}
		return listaRisultato;
	}

	/**
	 * @param vettore
	 * @param listaRisultato
	 * @param p
	 * @return
	 */
	public static ArrayList<File> SalvaPerUtente(JSONArray vettore, ArrayList<File> listaRisultato, Link p) {
		for (int i = 0; i < vettore.size(); i++) {

			File file = new File();
			JSONObject oggetto = (JSONObject) vettore.get(i);
			JSONObject sottoggetto = (JSONObject) oggetto.get("user");
			String id = (String) p.getId();
			String tipo = (String) p.getTipo();
			Long dimensione = (Long) p.getDimensione();
			String email = (String) sottoggetto.get("email");
			String nome = (String) sottoggetto.get("display_name");

			file.setId(id);
			file.setTipo(tipo);
			file.setDimensione(dimensione);
			file.setEmail(email);
			file.setNome(nome);

			listaRisultato.add(file);
		}
		return listaRisultato;
	}

}

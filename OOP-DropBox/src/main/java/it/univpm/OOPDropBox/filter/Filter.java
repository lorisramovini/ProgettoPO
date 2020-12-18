package it.univpm.OOPDropBox.filter;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;

import it.univpm.OOPDropBox.database.Database;
import it.univpm.OOPDropBox.exception.ParametroErratoException;
import it.univpm.OOPDropBox.exception.TokenErratoException;
import it.univpm.OOPDropBox.model.File;
import it.univpm.OOPDropBox.model.Link;
import it.univpm.OOPDropBox.model.Membri;
import it.univpm.OOPDropBox.model.StatCartella;
import it.univpm.OOPDropBox.model.StatMembro;
import it.univpm.OOPDropBox.model.StatUtenti;
import it.univpm.OOPDropBox.model.Tipi;
import it.univpm.OOPDropBox.utils.SalvaDati;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class Filter {

	/**
	 * Funzione che restituisce un vettore contenente i dati sui file condivisi
	 * presenti in ogni cartella 
	 * 
	 * @return ArrayList<StatCartella> contiene tutte le cartelle con i relativi link
	 * @throws TokenErratoException
	 */
	public static ArrayList<StatCartella> Stats() throws TokenErratoException {
		ArrayList<StatCartella> listaFinale = new ArrayList<StatCartella>();
		listaFinale.addAll(StatsHome());
		listaFinale.addAll(StatsFolder());
		return listaFinale;
	}

	/**
	 * funzione che restituisce un vettore contenente unicamente i dati sui
	 * file condivisi presenti nella cartella home
	 * 
	 * @return ArrayLista<StatCartella> contenente i dati della home
	 * @throws TokenErratoException
	 */
	public static ArrayList<StatCartella> StatsHome() throws TokenErratoException {
		ArrayList<StatCartella> listaTutte = new ArrayList<StatCartella>();
		StatCartella cartella1 = new StatCartella();

		JSONArray arr = (JSONArray) SalvaDati
				.SalvaApi("https://api.dropboxapi.com/2/files/list_folder", "{\r\n" + "    \"path\": \"\"\r\n" + "}")
				.get("entries");

		ArrayList<File> listaHome = new ArrayList<File>();
		listaHome = SalvaDati.SalvaPerCartella(arr, listaHome);

		Iterator<File> x = listaHome.iterator();
		while (x.hasNext()) {
			File x1 = x.next();
			Boolean flag = true;
			for (Link q : SalvaDati.getLink()) {
				if (q.getId().contentEquals(x1.getId())) {
					flag = false;
				}
			}
			if (flag) {
				x.remove();
			}
		}

		cartella1.setPercorso("");
		cartella1.setLink_totali((long) listaHome.size());
		cartella1.setSottocartella(Database.Sottocartelle(listaHome));
		ArrayList<Tipi> tipiCartella1 = Database.TrovaTipiDifferenti(listaHome);
		tipiCartella1 = Database.FilePerTipo(tipiCartella1, listaHome);

		cartella1.setFileTipo(tipiCartella1);
		listaTutte.add(cartella1);
		return listaTutte;
	}

	/**
	 * Funzione che restituisce una lista di tutte le cartelle presenti nella home
	 * e delle loro sottocartelle con i dati sui relativi fila condivisi
	 * 
	 * @return ArrayList<StatCartella> contente i dati delle varie cartelle
	 * @throws TokenErratoException
	 */
	public static ArrayList<StatCartella> StatsFolder() throws TokenErratoException {
		ArrayList<StatCartella> listaTutte = new ArrayList<StatCartella>();
		for (Link p : SalvaDati.getLink()) {
			ArrayList<File> listaCartella = new ArrayList<File>();
			StatCartella cartella = new StatCartella();
			if (p.getTag().contentEquals("folder")) {

				JSONArray arr1 = (JSONArray) SalvaDati.SalvaApi("https://api.dropboxapi.com/2/files/list_folder",
						"{\r\n" + "    \"path\":\"" + p.getPercorso() + "\"\r\n" + "}").get("entries");

				cartella.setLink_totali((long) arr1.size());

				listaCartella = SalvaDati.SalvaPerCartella(arr1, listaCartella);

				cartella.setSottocartella(Database.Sottocartelle(listaCartella));

				cartella.setPercorso(p.getPercorso());
				if (!listaCartella.isEmpty()) {
					ArrayList<Tipi> tipiCartella = Database.TrovaTipiDifferenti(listaCartella);

					tipiCartella = Database.FilePerTipo(tipiCartella, listaCartella);
					cartella.setFileTipo(tipiCartella);
				}
				listaTutte.add(cartella);
			}
		}
		return listaTutte;
	}

	/**
	 * Funzione che riceve in ingresso una lista di shared links e restituisce
	 * la lista degli utenti che condividono questi link; per ogni utente
	 * è indicata la quantità di file che condivide, suddivisi per tipo
	 * 
	 * @param sharedLink contiene una lista di shared links
	 * @return StatUtenti oggetto che ha al suo interno un vettore contenente
	 * tutti i membri con cui il profilo considerato condivide dei file
	 * @throws TokenErratoException
	 */
	public static StatUtenti StatMembri(ArrayList<Link> sharedLink) throws TokenErratoException {
		ArrayList<File> listaFile = new ArrayList<File>();
		for (Link p : sharedLink) {
			if (p.getTag().contentEquals("file")) {

				JSONArray arr = (JSONArray) SalvaDati.SalvaApi("https://api.dropboxapi.com/2/sharing/list_file_members",
						"{\r\n" + "    \"file\": \"" + p.getId() + "\"\r\n" + "}").get("users");

				listaFile = SalvaDati.SalvaPerUtente(arr, listaFile, p);
			}
		}

		ArrayList<Membri> listaMembri = Database.TrovaMembriDifferenti(listaFile);
		ArrayList<StatMembro> stat = new ArrayList<StatMembro>();
		StatUtenti tot = new StatUtenti();

		for (Membri p : listaMembri) {

			ArrayList<File> fileMembro = Database.FilePerMembro(listaFile, p);
			Long condivisi = (long) fileMembro.size();
			Long dimMedia = Database.CalcolaMedia(fileMembro, condivisi);
			ArrayList<Tipi> tipiMembro = Database.TrovaTipiDifferenti(fileMembro);
			tipiMembro = Database.FilePerTipo(tipiMembro, fileMembro);

			StatMembro membro = new StatMembro();
			membro.setNome(p.getNome());
			membro.setEmail(p.getEmail());
			membro.setFile(condivisi);
			membro.setDimMedia(dimMedia);
			membro.setFileTipo(tipiMembro);

			stat.add(membro);
		}

		tot.setNumUtenti(listaMembri.size());
		tot.setUtenti(stat);
		return tot;
	}

	/**
	 * Funzione usata per selezionare tra tutti i file presenti nelle varie cartelle solo quelli 
	 * della tipologia richiesta nel filtro
	 * 
	 * @param listaTutte contiene la lista dei file da filtrare
	 * @param filter contiene il filtro con operatore e tipo dati dall'utente
	 * @throws ParametroErratoException
	 */
	public static void Selezione(ArrayList<StatCartella> listaTutte, Filtro filter) throws ParametroErratoException {
		Iterator<StatCartella> q = listaTutte.iterator();
		Boolean flag = true;
		while (q.hasNext()) {
			StatCartella q1 = q.next();
			Iterator<Tipi> w = q1.getFileTipo().iterator();
			while (w.hasNext()) {
				Tipi w1 = w.next();
				if (filter.getOperatore().contentEquals("$eq") && !(w1.getTipo().contentEquals(filter.getTipo()))) {
					w.remove();
					flag = false;
					continue;
				} else if (filter.getOperatore().contentEquals("$not")
						&& (w1.getTipo().contentEquals(filter.getTipo()))) {
					flag = false;
					w.remove();
				}
			}
			if (q1.getFileTipo().isEmpty())
				q.remove();
		}
		if (listaTutte.isEmpty() || flag) {
			throw new ParametroErratoException("Parametro non presente nel dataset");
		}
	}

	/**
	 * Funzione che restituisce una lista di di utenti con i relativi faile
	 * selezionando tra tutti i file solamente quelli
	 * con il tipo corrispondente a quello richiesto dal filtro
	 * 
	 * @param filter contiene le richieste date dall'utente
	 * @return StatUtenti contiene solamente i file per utente che rispettano il filtro passato
	 * @throws TokenErratoException
	 * @throws ParametroErratoException
	 */
	public static StatUtenti SelezionePerMembro(Filtro filter) throws TokenErratoException, ParametroErratoException {
		StatUtenti tot = new StatUtenti();
		Iterator<Link> x = SalvaDati.getLink().iterator();
		ArrayList<Link> link = new ArrayList<Link>();
		if (filter.getOperatore().contentEquals("$eq")) {
			while (x.hasNext()) {
				Link x1 = x.next();
				if (x1.getTipo() == null || !(x1.getTipo().contentEquals(filter.getTipo()))) {
					continue;
				}
				link.add(x1);
			}
			if (link.isEmpty()) {
				throw new ParametroErratoException("Parametro non presente nel dataset");
			}
		} else { // ovviamente se gli unici tipi accettati sono eq e not e non è eq, allora è per
					// forza not, non serve fare il confronto
			Boolean flag = true;
			while (x.hasNext()) {
				Link x1 = x.next();
				if (x1.getTipo() == null) {
					continue;
				} else if (x1.getTipo().contentEquals(filter.getTipo())) {
					flag = false;
					continue;
				}
				link.add(x1);
			}
			if (flag) {
				throw new ParametroErratoException("Parametro non presente nel dataset");
			}
		}
		tot = Filter.StatMembri(link);
		return tot;
	}
}

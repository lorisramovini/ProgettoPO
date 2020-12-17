package it.univpm.OOPDropBox.database;

import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.OOPDropBox.model.File;
import it.univpm.OOPDropBox.model.Membri;
import it.univpm.OOPDropBox.model.Tipi;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */
public class Database {

	/**
	 * Funzione che restituisce il vettore contenente tutti i diversi membri
	 * che hanno accesso a dei file condivisi dall'account considerato
	 * 
	 * @param listaTotale parametro che contiene tutti i file che hanno uno shared links
	 * @return ArrayList<Membri> con email e nome di tutti i membri
	 */
	public static ArrayList<Membri> TrovaMembriDifferenti(ArrayList<File> listaTotale) {
		ArrayList<File> listaFile2 = new ArrayList<File>(listaTotale);
		ArrayList<Membri> listaMembri = new ArrayList<Membri>();

		Iterator<File> x = listaFile2.iterator();
		while (x.hasNext()) {
			Membri membro = new Membri();
			File x1 = x.next();
			if (listaMembri.isEmpty()) {
				membro.setEmail(x1.getEmail());
				membro.setNome(x1.getNome());
				listaMembri.add(membro);
				continue;
			}
			Iterator<Membri> y = listaMembri.iterator();
			Boolean flag = true;

			while (y.hasNext()) {
				Membri y1 = y.next();
				if (y1.getEmail().contentEquals(x1.getEmail()))
					flag = false;
			}
			if (flag) {
				membro.setEmail(x1.getEmail());
				membro.setNome(x1.getNome());
				listaMembri.add(membro);
			}
		}
		return listaMembri;
	}

	/**La funzione prende una lista di file e restituisce una lista contenente solamente
	 * le varie tipologie dei file
	 *
	 * @param listaTotale Arraylist<File> di cui si vogliono ottenere le varie tipologie
	 * @return ArrayList<Tipi> lista contenente solo i tipi dei file che gli sono stati dati (senza ripetizioni)
	 */
	public static ArrayList<Tipi> TrovaTipiDifferenti(ArrayList<File> listaTotale) {
		ArrayList<File> fileMembro2 = new ArrayList<File>(listaTotale);
		ArrayList<Tipi> tipiMembro = new ArrayList<Tipi>();
		Iterator<File> x = fileMembro2.iterator();
		while (x.hasNext()) {
			Tipi tipo = new Tipi();
			File x1 = x.next();
			String confronto = (String) x1.getTipo();
			if (confronto == null)
				continue;
			if (tipiMembro.isEmpty()) {
				tipo.setTipo(x1.getTipo());
				tipiMembro.add(tipo);
				continue;
			}
			Iterator<Tipi> y = tipiMembro.iterator();
			Boolean flag = true;

			while (y.hasNext()) {
				Tipi y1 = y.next();
				String confronto2 = (String) y1.getTipo();
				if (confronto2 == null)
					continue;
				if (y1.getTipo().contentEquals(confronto))
					flag = false;
			}
			if (flag) {
				tipo.setTipo(x1.getTipo());
				tipiMembro.add(tipo);
			}
		}
		return tipiMembro;
	}

	/**
	 * @param listaTotale
	 * @param membroCorrente
	 * @return ArrayList<File>
	 */
	public static ArrayList<File> FilePerMembro(ArrayList<File> listaTotale, Membri membroCorrente) {
		ArrayList<File> fileMembro = new ArrayList<File>();
		for (File r : listaTotale) {
			if (r.getEmail().contentEquals(membroCorrente.getEmail())) {
				fileMembro.add(r);
			}
		}
		return fileMembro;
	}

	/**
	 * Funzione che prende una lista di file e ne calcola la dimensione media
	 * 
	 * @param listaMembro ArrayList<File> contenente i file di cui si vuole calcolare la dimensione media
	 * @param numeroFile Long che rappresenta il numero totale di file presenti nella lista
	 * @return Long che indica la dimensione media dei file che sono stati passati alla funzione
	 */
	public static Long CalcolaMedia(ArrayList<File> listaMembro, Long numeroFile) {
		Long somma = (long) 0;
		for (File s : listaMembro) {
			somma += s.getDimensione();
		}
		Long media = somma / numeroFile;
		return media;
	}

	/**
	 * Funzione che, data una lista di tipologie di file, ne calcola per ognuna il numero di apparizioni
	 * in una determinata lista di File
	 * 
	 * @param listaTipi ArrayList<Tipi> contiene la lista di tutti i tipi che si vogliono identificare
	 * @param listaMembro Arraylist<File> contiene la lista dei file in cui bisogna fare il conteggio dei tipi
	 * @return ArrayList<Tipi> contiene per ogni tipo di file anche il numero
	 */
	public static ArrayList<Tipi> FilePerTipo(ArrayList<Tipi> listaTipi, ArrayList<File> listaMembro) {

		Iterator<Tipi> z = listaTipi.iterator();
		while (z.hasNext()) {
			Tipi z1 = z.next();
			int contatore = 0;
			Iterator<File> x = listaMembro.iterator();
			while (x.hasNext()) {
				File x1 = x.next();
				if (x1.getTipo() == null)
					continue;
				else if (x1.getTipo().contentEquals(z1.getTipo())) {
					contatore++;
				}
			}
			z1.setNumero(contatore);
		}
		return listaTipi;
	}

	/**
	 * funzione che data la lista degli shared link di una cartella
	 * restituisce il numero di sottocartelle presenti in essa
	 * 
	 * @param listaCartella Arraylist<File> contiene la lista di link presenti in una cartella
	 * @return Integer che rappresenta il numero di sottocartelle presenti
	 */
	public static Integer Sottocartelle(ArrayList<File> listaCartella) {
		Integer sottocartella = 0;
		for (File f : listaCartella) {
			if (f.getTag().contentEquals("folder"))
				sottocartella++;
		}
		return sottocartella;
	}
}

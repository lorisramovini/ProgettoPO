package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

import java.util.ArrayList;

public class StatUtenti {

	private Integer numUtenti;
	
	private ArrayList<StatMembro> utenti;

	public Integer getNumUtenti() {
		return numUtenti;
	}

	public void setNumUtenti(Integer numUtenti) {
		this.numUtenti = numUtenti;
	}

	public ArrayList<StatMembro> getUtenti() {
		return utenti;
	}

	public void setUtenti(ArrayList<StatMembro> utenti) {
		this.utenti = utenti;
	}
	
}

package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

import java.util.ArrayList;

public class StatCartella {

	private String percorso;

	private Long link_totali;
	
	private Integer sottocartella;

	private ArrayList<Tipi> fileTipo;
	
	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public Long getLink_totali() {
		return link_totali;
	}

	public void setLink_totali(Long link_totali) {
		this.link_totali = link_totali;
	}

	public ArrayList<Tipi> getFileTipo() {
		return fileTipo;
	}

	public void setFileTipo(ArrayList<Tipi> fileTipo) {
		this.fileTipo = fileTipo;
	}
	
	public Integer getSottocartella() {
		return sottocartella;
	}

	public void setSottocartella(Integer sottocartella) {
		this.sottocartella = sottocartella;
	}
	
	
}

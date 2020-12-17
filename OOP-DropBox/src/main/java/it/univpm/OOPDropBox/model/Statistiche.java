package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistiche {
	@JsonProperty("path_lower")
	private String percorso;
	@JsonProperty("preview_type")
	private String tipo;

	private Integer numero;

	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}

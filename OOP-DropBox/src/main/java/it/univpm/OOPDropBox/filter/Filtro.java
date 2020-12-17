package it.univpm.OOPDropBox.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

public class Filtro {
	
	@JsonProperty ("operatore")
	private String operatore;
	
	@JsonProperty ("parametro")
	private String tipo;

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

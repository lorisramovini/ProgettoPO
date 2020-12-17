package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

public class File extends Membri {
	private String id;

	private String tipo;
	
	private Long dimensione;
	
	private String tag;
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getDimensione() {
		return dimensione;
	}

	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}
	
}

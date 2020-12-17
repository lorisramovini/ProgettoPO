package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

public class Link {

	private String tag;
	private String url;
	private String id;
	private String nome;
	private String percorso;
	private String tipo;
	private Long dimensione;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public Long getDimensione() {
		return dimensione;
	}
	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}
}

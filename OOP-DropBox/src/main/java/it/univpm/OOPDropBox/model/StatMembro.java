package it.univpm.OOPDropBox.model;

/**
 * @author Lorenzo Piccioni, Loris Ramovini
 *
 */

import java.util.ArrayList;

public class StatMembro extends Membri {
	
	private Long file;
	
	private Long dimMedia;
	
	private ArrayList<Tipi> fileTipo;

	public Long getFile() {
		return file;
	}

	public void setFile(Long file) {
		this.file = file;
	}

	public Long getDimMedia() {
		return dimMedia;
	}

	public void setDimMedia(Long dimMedia) {
		this.dimMedia = dimMedia;
	}

	public ArrayList<Tipi> getFileTipo() {
		return fileTipo;
	}

	public void setFileTipo(ArrayList<Tipi> fileTipo) {
		this.fileTipo = fileTipo;
	}

}

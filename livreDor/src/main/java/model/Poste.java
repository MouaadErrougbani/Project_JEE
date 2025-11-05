package model;

import java.sql.Date;

public class Poste {
	private int id;
	private String auteur;
	private String titel;
	private String content ;
	private Date dateCreation; 
	private Date dateModification;
	
	public Poste() {
		super();
	}
	
	public Poste(int id, String auteur, String titel, String content, Date dateCreation,
			Date dateModification) {
		super();
		this.id = id;
		this.auteur = auteur;
		this.titel = titel;
		this.content = content;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	
	
}

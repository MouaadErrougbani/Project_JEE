package model;

import java.sql.Date;

public class Commentaire {
	private int id;
	private int idPoste;
	private String auteur;
	private String contene; 
	private Date dateCreation;
	
	public Commentaire() {
		super();
	}
	
	public Commentaire(int id, int idPoste, String auteur, String contene, Date dateCreation) {
		super();
		this.id = id;
		this.idPoste = idPoste;
		this.auteur = auteur;
		this.contene = contene;
		this.dateCreation = dateCreation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPoste() {
		return idPoste;
	}
	public void setIdPoste(int idPoste) {
		this.idPoste = idPoste;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getContene() {
		return contene;
	}
	public void setContene(String contene) {
		this.contene = contene;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
	
}

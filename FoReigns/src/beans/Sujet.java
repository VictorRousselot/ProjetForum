package beans;

import java.util.Date;

import org.joda.time.DateTime;

public class Sujet {
	
	private int idSujet;
	private String libelle;
	private Date dateCreation;
	private String createur;
	
	public int getIdSujet() {
		return idSujet;
	}
	public void setIdSujet(int idSujet) {
		this.idSujet = idSujet;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getCreateur() {
		return createur;
	}
	public void setCreateur(String createur) {
		this.createur = createur;
	}
}

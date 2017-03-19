package beans;

import java.util.Date;
public class Sujet {
	
	private int idSujet;
	private String libelle;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

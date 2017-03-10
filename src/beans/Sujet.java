package beans;

import java.util.Date;

import org.joda.time.DateTime;

public class Sujet {
	
	private int idSujet;
	private String nom;
	private Date dateCreation;
	private String auteur;
	
	public void setIdSujet(int idSujet){
		this.idSujet = idSujet;
	}
	public int getIdSujet(){
		return idSujet;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
}

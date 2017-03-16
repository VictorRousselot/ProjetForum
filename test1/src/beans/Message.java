package beans;

public class Message {
	private int idMessage;
	private String contenu;
	private int sujet;
	private String createur;
	
	public int getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getSujet() {
		return sujet;
	}
	public void setSujet(int sujet) {
		this.sujet = sujet;
	}
	public String getCreateur() {
		return createur;
	}
	public void setCreateur(String createur) {
		this.createur = createur;
	}
}

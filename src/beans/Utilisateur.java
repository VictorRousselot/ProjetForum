package beans;
public class Utilisateur {

    /* Propriétés du bean */

    private String pseudo;
	private String nom;
    private String prenom;
    private String email;
    private String mdp;

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }    
    
    public void setMdp( String mdp ) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }    
}

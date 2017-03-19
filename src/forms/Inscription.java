package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDAO;
import dao.DAOException;

public final class Inscription {
    private static final String CHAMP_NOM       = "nom";
    private static final String CHAMP_PRENOM    = "prenom";
    private static final String CHAMP_EMAIL     = "email";

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    private UtilisateurDAO utilisateurDao;

    public Inscription(UtilisateurDAO utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Utilisateur creerUtil( HttpServletRequest request) {
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String email = getValeurChamp( request, CHAMP_EMAIL );

        Utilisateur utilisateur = new Utilisateur();

        traiterNom( nom, utilisateur);
        traiterPrenom( prenom, utilisateur);
        traiterEmail( email, utilisateur);

        try {
            if ( erreurs.isEmpty() ) {
                utilisateurDao.creer(utilisateur);
                resultat = "Succès de la création du client.";
            } else {
                resultat = "Échec de la création du client.";
            }
        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return utilisateur;
    }

    private void traiterNom( String nom, Utilisateur utilisateur) {
        try {
            validationNom(nom);
        } catch (Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );
    }

    private void traiterPrenom( String prenom, Utilisateur utilisateur) {
        try {
            validationPrenom( prenom );
        } catch (Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        utilisateur.setPrenom( prenom );
    }

    private void traiterEmail( String email, Utilisateur utilisateur) {
        try {
            validationEmail( email );
        } catch (Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );
    }

    private void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom d'utilisateur." );
        }
    }

    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new Exception( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
        }
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }
    
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
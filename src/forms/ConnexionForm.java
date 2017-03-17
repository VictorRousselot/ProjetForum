package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;

public final class ConnexionForm {
	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_PASS   = "motdepasse";

	private String              resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
		/* Récupération des champs du formulaire */
		String pseudo = getValeurChamp( request, CHAMP_PSEUDO);
		String motDePasse = getValeurChamp( request, CHAMP_PASS );

		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setPseudo(pseudo);;
		utilisateur.setMdp( motDePasse );

		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}

		return utilisateur;
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

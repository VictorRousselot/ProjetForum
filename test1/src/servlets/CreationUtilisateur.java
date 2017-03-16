package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Utilisateur;


public class CreationUtilisateur extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID 	= 1L;
	public static final String CHAMP_NOM       	= "nomUtilisateur";
    public static final String CHAMP_PRENOM   	= "prenomUtilisateur";
    public static final String CHAMP_EMAIL    	= "emailUtilisateur";
    public static final String CHAMP_PSEUDO   	= "pseudoUtilisateur";
    public static final String CHAMP_MDP		= "mdpUtilisateur"; 
    
    public static final String ATT_UTILISATEUR	= "Utilisateur";
    public static final String ATT_MESSAGE     	= "message";
    public static final String ATT_ERREUR      	= "erreur";
 
    public static final String VUE          	= "/afficherUtilisateur.jsp";
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire
         */
        String nom 		= request.getParameter(CHAMP_NOM);
        String prenom	= request.getParameter(CHAMP_PRENOM);
        String email	= request.getParameter(CHAMP_EMAIL);
        String pseudo	= request.getParameter(CHAMP_PSEUDO);
        String mdp 		= request.getParameter(CHAMP_MDP);
        
        String message;
        boolean erreur;
        
        /*
         * Initialisation du message à afficher : si un des champs obligatoires
         * du formulaire n'est pas renseigné, alors on affiche un message
         * d'erreur, sinon on affiche un message de succès
         */

        if ( nom.trim().isEmpty() || pseudo.trim().isEmpty() || email.trim().isEmpty() || mdp.trim().isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creationUtilisateur.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un utilisateur.";
            erreur=true;
        } else 
        {
            message = "Utilisateur créé avec succès !";
            erreur=false;
        }

        /*
         * Création du bean Client et initialisation avec les données récupérées
         */

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom		(nom);
        utilisateur.setPrenom	(prenom);
        utilisateur.setEmail	(email);
        utilisateur.setPseudo	(pseudo);
        utilisateur.setMdp		(mdp);

        /* Ajout du bean et du message à l'objet requête */

        request.setAttribute( ATT_UTILISATEUR, utilisateur );
        request.setAttribute( ATT_MESSAGE,  message);
        request.setAttribute( ATT_ERREUR, erreur );


        /* Transmission à la page JSP en charge de l'affichage des données */

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }

}
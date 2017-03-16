package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TestJDBC {
	/* La liste qui contiendra tous les résultats de nos essais */
	private List<String> messages = new ArrayList<String>();

	public List<String> executerTests( HttpServletRequest request ) {
		try {
			messages.add("Chargement du driver...");
			Class.forName("com.mysql.jdbc.Driver");
			messages.add("Driver chargé");
		} catch(ClassNotFoundException e){
			messages.add("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br />"+e.getMessage());
		}

		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/forum";
		String utilisateur = "testUser";
		String motDePasse = "password";
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			messages.add( "Connexion à la base de données..." );
			connexion = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
			messages.add( "Connexion réussie !" );

			/* Création de l'objet gérant les requêtes */
			statement = (Statement) connexion.createStatement();
			messages.add( "Objet requête créé !" );

			/* Exécution d'une requête de lecture */
			resultat = statement.executeQuery( "SELECT * FROM Sujet;" );
			messages.add( "Requête \"SELECT * FROM Sujet;\" effectuée !" );

			while(resultat.next()){
				int idSujet = resultat.getInt("idSujet");
				String nom = resultat.getString("libelle");
				String date = resultat.getString("dateCreation");
				String auteur = resultat.getString("createur");
			}
		} catch(SQLException e){
			messages.add("Erreur lors de la connexion : <br />"+e.getMessage());
		} finally {
			messages.add("Fermeture de l'objet Resultset");
			if(resultat != null) {
				try {
					resultat.close();
				} catch(SQLException e){
				}
			}
			messages.add("Fermeture de l'objet Statement");
			if ( statement != null ) {
				try {
					statement.close();
				} catch ( SQLException ignore ) {
				}
			}
			messages.add( "Fermeture de l'objet Connection." );
			if ( connexion != null ) {
				try {
					connexion.close();
				} catch ( SQLException ignore ) {
				}
			}
		}
		return messages;
	}
}
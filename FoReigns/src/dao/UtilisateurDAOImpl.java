package dao;

import dao.DAOUtilitaire;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM Utilisateur";
	private static final String SQL_INSERT = "INSERT INTO Utilisateur (pseudo, nom, prenom, mail, mdp) VALUES (?, ?, ?, ?, MD5(?))";


	UtilisateurDAOImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public Utilisateur trouver(String nom) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_PSEUDO, false, nom);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				utilisateur = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return utilisateur;
	}
	
	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public void creer(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création du sujet, aucune ligne ajoutée dans la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}

/*
 * Simple méthode utilitaire permettant de faire la correspondance (le
 * mapping) entre une ligne issue de la table des utilisateurs (un
 * ResultSet) et un bean Utilisateur.
 */
private static Utilisateur map( ResultSet resultSet ) throws SQLException {
	Utilisateur utilisateur = new Utilisateur();
	utilisateur.setPseudo( resultSet.getString( "pseudo" ) );
	utilisateur.setNom(resultSet.getString("nom"));
	utilisateur.setEmail(resultSet.getString( "mail"));
	utilisateur.setMdp(resultSet.getString("mdp"));
	return utilisateur;
}
}

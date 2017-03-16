package dao;

import dao.DAOUtilitaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.Sujet;

public class SujetDaoImpl implements SujetDao {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_LIBELLE = "SELECT * FROM Sujet WHERE libelle = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM Sujet";
	private static final String SQL_SELECT_PAR_CREATEUR = "SELECT * FROM Sujet WHERE createur = ?";
	private static final String SQL_INSERT = "INSERT INTO Sujet (libelle, dateCreation, createur) VALUES (?, NOW(), ?)";


	SujetDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public Sujet trouver(String libelle) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Sujet sujet = null;
		
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_LIBELLE, false, libelle);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				sujet = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return sujet;
	}
	
	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public void creer(Sujet sujet ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_INSERT, true, sujet.getLibelle(), sujet.getCreateur());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création du sujet, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				sujet.setIdSujet( valeursAutoGenerees.getInt("idSujet"));
			} else {
				throw new DAOException( "Échec de la création du sujet en base, aucun ID auto-généré retourné." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}
	
	@Override
	public ArrayList<Sujet> recuperer() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Sujet> sujets = new ArrayList<>();
		Sujet sujet = null;
		String sql = "";
		
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			while ( resultSet.next() ) {
				sujet = map( resultSet );
				System.err.println(sujet.getLibelle());
				sujets.add(sujet);
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return sujets;
	}

/*
 * Simple méthode utilitaire permettant de faire la correspondance (le
 * mapping) entre une ligne issue de la table des utilisateurs (un
 * ResultSet) et un bean Utilisateur.
 */
private static Sujet map( ResultSet resultSet ) throws SQLException {
	Sujet sujet = new Sujet();
	sujet.setLibelle( resultSet.getString( "libelle" ) );
	sujet.setDateCreation(resultSet.getDate("dateCreation"));
	sujet.setCreateur(resultSet.getString("createur"));
	return sujet;
}
}

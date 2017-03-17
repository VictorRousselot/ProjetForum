package dao;

import dao.DAOUtilitaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.Message;
import beans.Sujet;

public class MessageDaoImpl implements MessageDao {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_SUJET = "SELECT * FROM Message WHERE sujet = ?";
	private static final String SQL_INSERT = "INSERT INTO Message (contenu, sujet, createur) VALUES (?, ?, ?)";


	MessageDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Message> trouver(String idSujet) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Message message = null;
		ArrayList<Message> messages = new ArrayList<Message>();
		String sql = "";
		
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_SUJET, false, idSujet);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			while ( resultSet.next() ) {
				message = map( resultSet );
				messages.add(message);
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return messages;
	}
	
	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public void creer(Message message) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_INSERT, true, message.getContenu(), message.getSujet(), message.getCreateur());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création du sujet, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				message.setIdMessage(valeursAutoGenerees.getInt("idMessage"));
			} else {
				throw new DAOException( "Échec de la création du sujet en base, aucun ID auto-généré retourné." );
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
private static Message map( ResultSet resultSet ) throws SQLException {
	Message message = new Message();
	message.setContenu(resultSet.getString("contenu"));
	message.setSujet(resultSet.getInt("sujet"));
	message.setCreateur(resultSet.getString("createur"));
	return message;
}
}

package dao;

import java.util.ArrayList;

import beans.Message;

public interface MessageDao {
	void creer (Message message) throws DAOException;
	
	ArrayList<Message> trouver (String idSujet) throws DAOException;
}

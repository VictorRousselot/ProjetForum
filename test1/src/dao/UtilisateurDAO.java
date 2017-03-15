package dao;

import com.sdzee.beans.Utilisateur;

public interface UtilisateurDAO {
	 void creer(Utilisateur utilisateur ) throws DAOException;

	 Utilisateur trouver(String nom) throws DAOException;

}

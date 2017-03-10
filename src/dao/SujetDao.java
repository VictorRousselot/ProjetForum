package dao;

import beans.Sujet;

public interface SujetDao {

    void creer( Sujet utilisateur ) throws DAOException;

    Sujet trouver( String email ) throws DAOException;

}

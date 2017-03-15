package dao;

import java.util.ArrayList;

import com.sdzee.beans.Sujet;

public interface SujetDao {

    void creer(Sujet sujet) throws DAOException;

    Sujet trouver(String libelle) throws DAOException;
    
    ArrayList<Sujet> recuperer() throws DAOException;
}

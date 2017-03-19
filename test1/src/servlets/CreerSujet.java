package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Sujet;
import dao.*;

public class CreerSujet extends HttpServlet {
	/**
	 * 
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_TITRE = "titreSujet";
	public static final String ATT_DESCRIPTION = "descriptifSujet";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT= "resultat";
	public static final String VUE = "/WEB-INF/forum.jsp";
	
	private SujetDao sujetDao;
	
	public void init() throws ServletException {
		this.sujetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		
		String titre = request.getParameter(ATT_TITRE);
		String desc = request.getParameter(ATT_DESCRIPTION);
		HashMap<String, String> erreurs = new HashMap<String, String>();
		String resultat;
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		pseudo = "coco722";
		
		if(titre.isEmpty()){
			erreurs.put("titre", "Non valide !");
		}
		
		if(desc.isEmpty()){
			erreurs.put("description", "Non valide !");
		}
		
		if(erreurs.isEmpty()){
			Sujet sujet = new Sujet();
			sujet.setLibelle(titre);
			sujet.setDescription(desc);
			sujet.setCreateur(pseudo);
			sujetDao.creer(sujet);
			resultat = "Ca marche !";
		}
		else {
			resultat = "Non conforme";
		}
		
		request.setAttribute(ATT_ERREURS, erreurs);
		request.setAttribute(ATT_RESULTAT, resultat);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, reponse);
	}
}
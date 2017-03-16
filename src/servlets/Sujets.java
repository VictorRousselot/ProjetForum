package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Sujet;
import dao.*;

public class Sujets extends HttpServlet {
	/**
	 * 
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SUJET = "sujet";
	public static final String VUE = "/WEB-INF/sujets.jsp";
	
	private SujetDao sujetDao;
	
	public void init() throws ServletException {
		this.sujetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		ArrayList<Sujet> mesSujets = new ArrayList<Sujet>();
		mesSujets = sujetDao.recuperer();
		request.setAttribute("maListe", sujetDao.recuperer());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, reponse);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		this.doPost(request, reponse);
	}
}


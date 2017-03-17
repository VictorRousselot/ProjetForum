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

public class ListerSujet extends HttpServlet {
	/**
	 * 
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LISTE = "sujets";
	public static final String VUE = "/WEB-INF/forum.jsp";
	
	private SujetDao sujetDao;
	
	public void init() throws ServletException {
		this.sujetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
	
		ArrayList<Sujet> sujets = new ArrayList<Sujet>();
		sujets = sujetDao.recuperer();
		
		request.setAttribute(ATT_LISTE, sujets);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, reponse);
	}
}

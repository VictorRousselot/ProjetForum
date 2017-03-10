package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import dao.*;

public class Sujets extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SUJET = "sujet";
	public static final String VUE = "WEB-INF/sujets.jsp";
	
	private SujetDao sujetDao;
	
	public void init() throws ServletException {
		this.sujetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
	}
	
	public void doPost()
}

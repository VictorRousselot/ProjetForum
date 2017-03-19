package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Sujet;
import dao.*;

public class Accueil extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LISTE = "sujets";
	public static final String VUE = "/WEB-INF/forum.jsp";
	
	private SujetDao sujetDao;
      
	public void init() throws ServletException {
		this.sujetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Sujet> sujets = new ArrayList<Sujet>();
		sujets = sujetDao.recuperer();
		
		request.setAttribute(ATT_LISTE, sujets);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

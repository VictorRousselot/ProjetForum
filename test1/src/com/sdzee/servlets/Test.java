package com.sdzee.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Coyote;

public class Test extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		/* Création et initialisation du message. */
		String paramAuteur = request.getParameter( "auteur" );
		String message = "Transmission de variables : OK ! " + paramAuteur;

		/* Création du bean */
		Coyote premierBean = new Coyote();
		/* Initialisation de ses propriétés */
		premierBean.setNom( "Coyote" );
		premierBean.setPrenom( "Wile E." );

		/* Stockage du message et du bean dans l'objet request */
		request.setAttribute( "test", message );
		request.setAttribute( "coyote", premierBean );

		/* Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	} 
} 
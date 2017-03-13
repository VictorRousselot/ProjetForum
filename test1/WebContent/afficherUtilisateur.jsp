<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un utilisateur</title>
        <link type="text/css" rel="stylesheet" href="style.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
       <c:import url="menu.jsp" />
       <div id="corps">
        <p class="info">${ message }</p>
        
        <c:if test="${ !erreur }">
        
        <p>Nom : <c:out value="${ utilisateur.nom }"/></p>
        <p>Prénom : <c:out value="${ utilisateur.prenom }"/></p>
        <p>Adresse : <c:out value="${ utilisateur.adresse }"/></p>
        <p>Numéro de téléphone : <c:out value="${ utilisateur.telephone }"/></p>
        <p>Email : <c:out value="${ utilisateur.email }"/></p>
    	
    	</c:if>
    	
    	</div>
    </body>
</html> 
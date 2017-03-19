<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="menu" id="titre">

	<p><b><a href="<c:url value="/forum.jsp"/>" style="text-decoration:none">Reigns - Forum</a></b></p>

<div id="user">
    <p><a href="<c:url value="/inc_utilisateur_form.jsp"/>">Créer un nouvel utilisateur</a></p>
	<p><a href="<c:url value="/connexion.jsp"/>">Connexion</a></p>
</div>


</div>


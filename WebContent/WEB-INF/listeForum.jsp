<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des sujets disponibles</title>
        <link type="text/css" rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div>
            <form method="get" action="<c:url value="/listeForum"/>">
                <fieldset>
                    <legend>Liste des sujets disponibles</legend>
                    <c:forEach items="${sujets}" var="sujet">
                    	<label for="titreSujet">Titre du sujet <span class="requis">*</span></label>
						<input type="text" id="titreSujet" name="titreSujet" value="${sujet.libelle}" size="30" maxlength="60" />
						<br />
                    </c:forEach>
                </fieldset>
            </form>
        </div>
    </body>
</html> 
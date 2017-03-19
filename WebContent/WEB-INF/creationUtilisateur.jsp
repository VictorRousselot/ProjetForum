<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un utilisateur</title>
        <link type="text/css" rel="stylesheet" href="style.css" />
    </head>
    <body>
        <c:import url="menu.jsp" />
        <div>
            <form method="get" action="<c:url value="/creationUtilisateur"/>">
                <fieldset>
                    <legend>Informations utilisateur</legend>
                    <c:import url="inc_utilisateur_form.jsp" />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html> 
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un sujet</title>
        <link type="text/css" rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div>
            <form method="get" action="<c:url value="/creationSujet"/>">
                <fieldset>
                    <legend>Création d'un sujet</legend>
                    <c:import url="inc_creaSujet_form.jsp" />
                	<br /><input type="submit" value="Valider"  />                    
                </fieldset>
            </form>
        </div>
    </body>
</html> 
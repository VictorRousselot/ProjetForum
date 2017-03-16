<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="style.css" />
    </head>
    <body>
        <c:import url="menu.jsp" />
        <div>
            <form method="get" action="<c:url value="/connexion"/>">
                <fieldset>
                    <legend> Connexion </legend>
                    <c:import url="inc_connexion_form.jsp" />                    
                </fieldset>
                <input type="submit" value="Valider"  />
            </form>
        </div>
    </body>
</html> 
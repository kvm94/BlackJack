<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="Resources/CSS/general.css" />
    </head>
    <body>
        <form method="post" action="connection">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="nom">Adresse email <span class="requis">*</span></label>
                <input type="email" id="mail" name="mail" value="<c:out value="${user.mail}"/>" size="20" maxlength="60" />
                <span class="error">${model.errors['mail']}</span>
                <br/>

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="error">${model.errors['password']}</span>
				<br/>

                <input type="submit" value="Connexion" class="sansLabel" />
                <br/>
                
                <p class="${empty model.errors ? 'succes' : 'erreur'}">${model.result}</p>
                
                <c:if test="${!empty sessionScope.sessionUser}">
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.mail}</p>
                </c:if>
                
            </fieldset>
        </form>
    </body>
</html>
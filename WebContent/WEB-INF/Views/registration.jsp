<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="Resources/CSS/general.css" />
    </head>
	<body>
		<form method="post" action="registration">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
                                
                <label for="mail">Adresse email <span class="requis">*</span></label>
                <input type="email" id="mail" name="mail" value="<c:out value="${user.mail}"/>" size="20" maxlength="60" />
                <span class="error">${model.errors['mail']}</span>
                <br />
                
                <label for="password">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="error">${model.errors['password']}</span>
                <br />

                <label for="confirmPassword">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmPassword" name="confirmPassword" value="" size="20" maxlength="20" />
                <span class="error">${model.errors['confirmPassword']}</span>
                <br />

                <label for="name">Nom </label>
                <input type="text" id="name" name="name" value="<c:out value="${user.name}"/>" size="20" maxlength="20" />
                <span class="error">${model.errors['name']}</span>
                <br />
                
                <label for="firstName">Prénom </label>
                <input type="text" id="firstName" name="firstName" value="<c:out value="${user.firstName}"/>" size="20" maxlength="20" />
                <span class="error">${model.errors['firstName']}</span>
                <br />
                
                <label for="birthDate">Date de naissance </label>
                <input type="date" id="birthDate" name="birthDate" value="<c:out value="${user.birthDate}"/>" size="20" maxlength="20" />
                <span class="error">${model.errors['birthDate']}</span>
                <br />
                
                <input type="submit" value="Inscription" class="sansLabel" />
                <br />                
                
                <p class="${empty model.errors ? 'succes' : 'erreur'}">${model.result}</p>
            </fieldset>
        </form>
	</body>
</html>
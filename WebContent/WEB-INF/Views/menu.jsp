<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Menu</title>
<link type="text/css" rel="stylesheet" href="Resources/CSS/general.css" />
</head>
<body>
	<fieldset>
		<legend>Menu</legend>
		<p>
			Bienvenue
			<c:out value="${sessionScope.sessionUser.mail}" />
		</p>
		<form method="post" action="play">
			<input type="submit" value="Play" /> <br />
		</form>
		<form method="get" action="transaction">
			<input type="submit" name="transaction" value="Transaction" /> <br />
		</form>
		<form method="post" action="deconnection">
			<input type="submit" name="deconnection" value="Se déconnecter" /> <br />
		</form>
	</fieldset>
</body>
</html>
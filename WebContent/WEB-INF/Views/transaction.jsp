<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Transaction</title>
<link type="text/css" rel="stylesheet" href="Resources/CSS/general.css" />
</head>
<body>
	<div id="capitalSection" name="capitalSection">
		<fieldset>
			<legend>Capital</legend>
				<p>Vous avez <c:out value="${sessionScope.sessionUser.capital}" /> Jeton(s).</p>
		</fieldset>
	</div>
	<div id="buySection" name="buySection">
		<fieldset>
		<legend>Achat</legend>
			<form method="post" action="buy">
				<p>Vous pouvez indiquer le nombre de jetons que vous voulez acheter via champ.</p>
				<input type="number" id="buyAmount" name="buyAmount" value="" size="10" maxlength="20" /> 
				<span class="error">${model.errors['amount']}</span>
				<input type="submit" value="Buy"/> 
				<br />
			</form>
		</fieldset>
	</div>
	<div id="sellSection" name="sellSection">
		<fieldset>
		<legend>Vente</legend>
			<form method="post" action="sell">
				<p>Vous pouvez indiquer le nombre de jetons que vous voulez vendre via champ.</p>
				<input type="number" id="sellAmount" name="sellAmount" value="" size="10" maxlength="20" /> 
				<span class="error">${model.errors['amount']}</span>
				<input type="submit" value="Sell"/> 
				<br />
			</form>
		</fieldset>
	</div>
	<p class="${empty model.errors ? 'succes' : 'erreur'}">${model.result}</p>
	<form action="menu" method="get">
		<input type="submit" value="Retour">
	</form>
</body>
</html>
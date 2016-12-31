<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BlackJack</title>
<link type="text/css" rel="stylesheet" href="Resources/CSS/general.css" />
</head>
<body>
	<div id="capitalSection" name="capitalSection">
		<fieldset>
			<legend>Capital</legend>
				<p>Il vous reste <c:out value="${sessionScope.sessionUser.capital}" /> Jeton(s).</p>
		</fieldset>
	</div>
	<c:if test="${!empty turn.userHand}">
	<div id="handCroupierSection" name="handCroupierSection">
		<fieldset>
		<legend>Main du croupier</legend>
			<c:if test="${turn.isWin() == -2}">
				<p>Carte 1 : ${turn.croupierHand.get(0).getName()}</p>
				<br />
			</c:if>
			<c:if test="${turn.isWin() != -2}">
				<p>${model.printHand(turn.croupierHand)}</p>
			</c:if>
		</fieldset>
	</div>
	<div id="handUserSection" name="handUserSection">
		<fieldset>
		<legend>Votre main</legend>
			<p>${model.printHand(turn.userHand)}</p>
		</fieldset>
	</div>
	</c:if>
	<div id="infoSection" name="infoSection">
		<fieldset>
		<legend>Information</legend>
			<c:if test="${empty turn}">
				<p>Veuillez saisir votre mise</p>
			</c:if>
			<c:if test="${!empty turn.bet && turn.isWin() == -2}">
				<p>Le croupier a ${turn.croupierHand.get(0).getValue()}</p>
				<p>Vous avez ${model.setHandValue(turn.userHand)}</p>
			</c:if>
			<c:if test="${!empty turn.bet && turn.isWin() != -2}">
				<p>Le croupier a ${model.setHandValue(turn.croupierHand)}</p>
				<p>Vous avez ${model.setHandValue(turn.userHand)}</p>
				<br/>
				<c:if test="${turn.isWin() == 2}">
					<p>Vous avez fait un BlackJack directement ! Vous gagnez ${turn.bet * (3/2)} Jetons</p>
				</c:if>
				<c:if test="${turn.isWin() == 1}">
					<p><c:if test="${turn.getCroupierScore() > 21}">Le croupier a dépassé 21 ! </c:if>Vous avez gagné ${turn.bet} Jetons</p>
				</c:if>
				<c:if test="${turn.isWin() == 0}">
					<p>Vous avez gagné ${turn.bet} Jetons</p>
				</c:if>
				<c:if test="${turn.isWin() == -1}">
					<p>Vous avez perdu ${turn.bet} Jetons</p>
				</c:if>
			</c:if>
		</fieldset>
	</div>
	<div id="playSection" name="playSection">
		<c:if test="${!empty turn.userHand }">
			<fieldset>
			<legend>Que faire ?</legend>
				<c:if test="${turn.isWin() == -2}">
					<form method="post" action="pick">
						<input type="submit" value="Tirer une carte" />
					</form>
					<form method="post" action="stopTurn">
						<input type="submit" value="S'arrêter" />
					</form>
				</c:if>
				<c:if test="${turn.isWin() != -2}">
					<form method="post" action="play">
						<input type="submit" value="Rejouer" />
					</form>
					<form method="post" action="menu">
						<input type="submit" value="Quitter" />
					</form>
				</c:if>
			</fieldset>
		</c:if>
	</div>
	<div id="betSection" name="betSection">
		<c:if test="${empty turn || turn.isWin() == -2}">
			<fieldset>
			<legend>Mise</legend>
				<c:if test="${empty turn}">
					<form method="post" action="bet">
						<p>Vous pouvez indiquer votre mise via ce champ.</p>
						<input type="number" id="bet" name="bet" value="" size="10" maxlength="20" /> 
						<span class="error">${model.errors['bet']}</span>
						<br />
						<input type="submit" value="Bet"/> 
						<br />
						<p class="${empty model.errors ? 'succes' : 'erreur'}">${model.result}</p>
					</form>
				</c:if>
				<c:if test="${!empty turn.bet}">
					<p>Vous avez miser ${turn.bet} Jeton(s) .</p>
				</c:if>
			</fieldset>
		</c:if>
	</div>
</body>
</html>
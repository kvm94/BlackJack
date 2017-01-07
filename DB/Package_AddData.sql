CREATE OR REPLACE PACKAGE AddData
AS
	procedure addGame			(dateGame number, nbrTurns number, resultGame number, idUser number);
	procedure addTransaction	(dateTransaction number,var_amount number,idUser number);
	procedure addTurn			(var_win number,var_croupierScore number,var_useScore number,var_bet number,var_idGame number);
	procedure addUser			(login varchar,passwd  varchar,name	varchar,first_name varchar,birth_date number,mail varchar,capital number);
	procedure updateGame		(dateGame number,nbrTurns number,resultGame number,idUser number,idGame number);
	procedure updateUser		(var_capital number,idUser number);
END AddData;
/

CREATE OR REPLACE PACKAGE BODY AddData
IS

	procedure addGame(
			dateGame number,
			nbrTurns number,
			resultGame number,
			idUser number
			) 
	as
	begin
		INSERT INTO GAMES (date_game, nbr_turns, result_game, id_user) 
		VALUES (dateGame, nbrTurns, resultGame, idUser);
	end addGame;

	procedure addTransaction(
			dateTransaction number,
			var_amount number,
			idUser number
			) 
	as
	begin
		INSERT INTO transactions (date_transaction, amount, id_user) 
		VALUES (dateTransaction, var_amount, idUser);
	end addTransaction;

	procedure addTurn(
			var_win number,
			var_croupierScore number,
			var_useScore number,
			var_bet number,
			var_idGame number
			) 
	as
	begin
		INSERT INTO TURNS (win, croupier_score, user_score, bet, id_game) 
		VALUES (var_win, var_croupierScore, var_useScore, var_bet, var_idGame);
	end addTurn;

	procedure addUser(
			login varchar,
			passwd  varchar,
			name	varchar,
			first_name varchar,
			birth_date number,
			mail varchar,
			capital number
			) 
	as
	begin
		INSERT INTO USERS (login, password, name, first_name, birth_date, mail, capital) 
		VALUES (login,passwd, name, first_name, birth_date, mail, capital);
	end addUser;

	procedure updateGame(
			dateGame number,
			nbrTurns number,
			resultGame number,
			idUser number,
			idGame number
			) 
	as
	begin
		UPDATE GAMES
		SET date_game = dateGame,
			nbr_turns = nbrTurns,
			result_game = resultGame,
			id_user = idUser
		WHERE id_game = idGame;
	end updateGame;

	procedure updateUser(
			var_capital number,
			idUser number
			) 
	as
	begin
		UPDATE USERS
		SET capital = var_capital
		WHERE id_user = idUser;
	end updateUser;

END AddData;
/
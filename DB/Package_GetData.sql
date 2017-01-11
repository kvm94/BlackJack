CREATE OR REPLACE PACKAGE GetData --Création du package
AS
	--Définitions des procédures
	procedure findGame				(id number, recordset OUT SYS_REFCURSOR);
	procedure findGameByUser		(id number, recordset OUT SYS_REFCURSOR);
	procedure findTransactionByUser	(id number, recordset OUT SYS_REFCURSOR);
	procedure findTurnByGame		(id number, recordset OUT SYS_REFCURSOR);
	procedure findUser				(var_mail varchar, var_passwd varchar, recordset OUT SYS_REFCURSOR);
	procedure getIdGame				(var_date number, var_nbrTurns number, var_resultGame number, var_idUser number, id out number);
	
	--Exception
	fk_violationException EXCEPTION;
    PRAGMA EXCEPTION_INIT(fk_violationException,-2291);
END GetData;
/

--Corp du package et des procedures
CREATE OR REPLACE PACKAGE BODY GetData
IS
	fk_violationException EXCEPTION;
    PRAGMA EXCEPTION_INIT(fk_violationException,-2291);
	
	procedure findGame(id number, recordset OUT SYS_REFCURSOR) 
	as
	begin
		OPEN recordset FOR
		SELECT * FROM GAMEs WHERE id_game = id;
	end findGame;
	
	procedure findGameByUser(id number, recordset OUT SYS_REFCURSOR) 
	as
	begin
		OPEN recordset FOR
		SELECT * FROM GAMEs WHERE id_user = id;
	end findGameByUser;
	
	procedure findTransactionByUser(id number, recordset OUT SYS_REFCURSOR) 
	as
	begin
		OPEN recordset FOR
		SELECT * FROM TRANSACTIONS WHERE id_user = id;
	end findTransactionByUser;

	procedure findTurnByGame(id number, recordset OUT SYS_REFCURSOR) 
	as
	begin
		OPEN recordset FOR
		SELECT * FROM TURNS WHERE id_game = id;
	end findTurnByGame;

	procedure findUser(var_mail varchar, var_passwd varchar, recordset OUT SYS_REFCURSOR) 
	as
	begin
		OPEN recordset FOR
		SELECT * FROM USERS WHERE MAIL = var_mail and PASSWORD = var_passwd;
	end findUser;
	
	procedure getIdGame(var_date number, var_nbrTurns number, var_resultGame number, var_idUser number, id out number) 
	as
	begin
		--On récupère uniquement la dernière game.
		SELECT id_game into id
		FROM (
			--Renvois tout les id possibles (peut en avoir plusieurs et créer un erreur)
			SELECT id_game FROM GAMES 
			WHERE 
			date_game = var_date and
			nbr_turns = var_nbrTurns and
			result_game = var_resultGame and
			id_user = var_idUser
			ORDER BY id_game DESC
		)
		WHERE rownum <= 1;
    
	end getIdGame;

END GetData;
/

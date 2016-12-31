create or replace procedure addGame(
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
/
create or replace procedure updateGame(
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
/
create or replace procedure getidgame(var_date number, var_nbrTurns number, var_resultGame number, var_idUser number, id out number) 
as
begin
       SELECT id_game into id FROM GAMES 
		WHERE 
		date_game = var_date and
		nbr_turns = var_nbrTurns and
		result_game = var_resultGame and
		id_user = var_idUser;
        ORDER BY id_game DESC
       )
	WHERE rownum <= 1;
    
end getidgame;
/
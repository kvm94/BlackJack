create or replace procedure addturn(
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
end addturn;
/
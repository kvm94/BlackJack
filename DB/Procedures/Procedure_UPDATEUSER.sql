create or replace procedure updateUser(
			var_capital number,
			idUser number
			) 
as
begin
  UPDATE USERS
	SET capital = var_capital
		WHERE id_user = idUser;
end updateUser;
/
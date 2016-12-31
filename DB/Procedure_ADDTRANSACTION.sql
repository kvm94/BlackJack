create or replace procedure addtransaction(
			dateTransaction number,
			var_amount number,
			idUser number
			) 
as
begin
  INSERT INTO transactions (date_transaction, amount, id_user) 
  VALUES (dateTransaction, var_amount, idUser);
end addtransaction;
/
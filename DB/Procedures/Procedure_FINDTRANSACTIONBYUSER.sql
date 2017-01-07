create or replace procedure findtransactionByUser(id number, recordset OUT SYS_REFCURSOR) 
as
begin
  OPEN recordset FOR
    SELECT * FROM TRANSACTIONS WHERE id_user = id;
end FINDTRANSACTIONBYUSER;
/
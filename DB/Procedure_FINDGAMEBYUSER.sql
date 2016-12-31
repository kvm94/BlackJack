create or replace procedure findGameByUser(id number, recordset OUT SYS_REFCURSOR) 
as
begin
  OPEN recordset FOR
    SELECT * FROM GAMEs WHERE id_user = id;
end FINDGAMEBYUSER;
/
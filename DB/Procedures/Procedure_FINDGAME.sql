create or replace procedure findGame(id number, recordset OUT SYS_REFCURSOR) 
as
begin
  OPEN recordset FOR
    SELECT * FROM GAMEs WHERE id_game = id;
end FINDGAME;
/
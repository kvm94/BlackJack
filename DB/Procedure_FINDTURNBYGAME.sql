create or replace procedure findturnbygame(id number, recordset OUT SYS_REFCURSOR) 
as
begin
  OPEN recordset FOR
    SELECT * FROM TURNS WHERE id_game = id;
end findturnbygame;
/
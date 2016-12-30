create or replace procedure findUser(var_mail varchar, var_passwd varchar, recordset OUT SYS_REFCURSOR) 
as
begin
  OPEN recordset FOR
    SELECT * FROM USERS WHERE MAIL = var_mail and PASSWORD = var_passwd;
end FINDUSER;
/
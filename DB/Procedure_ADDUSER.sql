create or replace procedure addUser(
			login varchar,
			passwd  varchar,
			name	varchar,
			first_name varchar,
			birth_date number,
			mail varchar,
			capital number
			) 
as
begin
  INSERT INTO USERS (login, password, name, first_name, birth_date, mail, capital) 
  VALUES (login,passwd, name, first_name, birth_date, mail, capital);
end ADDUSER;
/
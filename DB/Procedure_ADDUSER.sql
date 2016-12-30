create or replace procedure addUser(
			passwd  varchar,
			name	varchar,
			first_name varchar,
			birth_date number,
			mail varchar,
			capital number
			) 
as
begin
  INSERT INTO USERS (password, name, first_name, birth_date, mail, capital) 
  VALUES ('passwd', 'name', 'first_name', birth_date, 'mail', capital);
end ADDUSER;
/
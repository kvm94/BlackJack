/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g           					*/
/* Nom de DB :  CASINO				           					*/
/* Date de création :  10/12/2016 15:47:00     					*/
/*==============================================================*/

/*==============================================================*/
/* Table : USERS                               					*/
/*==============================================================*/
create table USERS (
   ID_USER 			INTEGER 			not null,
   LOGIN 			VARCHAR2(100) 		not null,
   PASSWORD 		VARCHAR2(100) 		not null,      
   NAME 			VARCHAR2(100),
   FIRST_NAME 		VARCHAR2(100),
   BIRTH_DATE 		INTEGER,
   MAIL 			VARCHAR2(100),
   CAPITAL 			INTEGER 			not null,
   constraint PK_USERS primary key (ID_USER)
);

/*==============================================================*/
/* Table : GAMES                               					*/
/*==============================================================*/
create table GAMES (
   ID_GAME 			INTEGER 			not null,
   DATE_GAME 		INTEGER				not null,
   NBR_TURNS 		INTEGER 			not null,      
   RESULT_GAME 		INTEGER 			not null,
   ID_USER 			INTEGER 			not null,
   constraint PK_GAMES primary key (ID_GAME)
);

/*==============================================================*/
/* Index : PLAY_FK                             					*/
/*==============================================================*/
create index PLAY_FK on GAMES (
   ID_USER ASC
);

/*==============================================================*/
/* Table : TURNS                               					*/
/*==============================================================*/
create table TURNS (
   ID_TURN 			INTEGER 			not null,
   WIN				INTEGER 			not null,
   CROUPIER_SCORE	INTEGER 			not null,      
   USER_SCORE 		INTEGER				not null,
   BET 				INTEGER				not null,
   ID_GAME 			INTEGER				not null,
   constraint PK_TURNS primary key (ID_TURN),
   constraint CHK_WIN check (WIN = 1 or WIN = 0)
);

/*==============================================================*/
/* Index : INCLUDE_FK                          					*/
/*==============================================================*/
create index INCLUDE_FK on TURNS (
   ID_GAME ASC
);

/*==============================================================*/
/* Table : TRANSACTIONS                        					*/
/*==============================================================*/
create table TRANSACTIONS (
   ID_TRANSACTION 	INTEGER 			not null,
   DATE_TRANSACTION	INTEGER				not null,
   AMOUNT			INTEGER 			not null,      
   ID_USER 			INTEGER				not null,
   constraint PK_TRANSACTIONS primary key (ID_TRANSACTION)
);

/*==============================================================*/
/* Index : PERFORM_FK                          					*/
/*==============================================================*/
create index PERFORM_FK on TRANSACTIONS (
   ID_USER ASC
);

/*==============================================================*/
/* CONSTRAINTS FOREIGN KEY                    					*/
/*==============================================================*/

alter table GAMES
   add constraint FK_GAMES_PLAY_USERS foreign key (ID_USER)
      references USERS (ID_USER) on delete cascade;

alter table TURNS
   add constraint FK_TURNS_INCLUDE_GAMES foreign key (ID_GAME)
      references GAMES (ID_GAME) on delete cascade;
	  
alter table TRANSACTIONS
   add constraint FK_TRANSACTIONS_PLAY_USERS foreign key (ID_USER)
      references USERS (ID_USER) on delete cascade;
	  
/*==============================================================*/
/* AUTO INCREMENT			                  					*/
/*==============================================================*/
create sequence USERS_SEQ start with 1;

create or replace trigger USERS_BIR
before insert on USERS
for each row

begin
  select USERS_SEQ.nextval
  into   :new.ID_USER
  from   dual;
end;
/

create sequence GAMES_SEQ start with 1;

create or replace trigger GAMES_BIR
before insert on GAMES
for each row

begin
  select GAMES_SEQ.nextval
  into   :new.ID_GAME
  from   dual;
end;
/

create sequence TURNS_SEQ START with 1;

create or replace trigger TURNS_BIR
before insert on TURNS
for each row

begin
  select TURNS_SEQ.nextval
  into   :new.ID_TURN
  from   dual;
end;
/

create sequence TRANSACTIONS_SEQ START with 1;

create or replace trigger TRANSACTIONS_BIR
before insert on TRANSACTIONS
for each row

begin
  select TRANSACTIONS_SEQ.nextval
  into   :new.ID_TRANSACTION
  from   dual;
end;
/
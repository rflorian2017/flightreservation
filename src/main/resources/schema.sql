-- Create table
create table USER
(
  USER_ID      BIGINT not null,
  USER_NAME    VARCHAR(36) not null,
  PASSWORD 	   VARCHAR(128) not null,
  ACTIVE 	   BIT not null 
) ;
--  
alter table USER
  add constraint USER_PK primary key (USER_ID);
 
alter table USER
  add constraint USER_UK unique (USER_NAME);
  
  -- Create table
create table ROLE
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
) ;
--  
alter table ROLE
  add constraint ROLE_PK primary key (ROLE_ID);
 
alter table ROLE
  add constraint ROLE_UK unique (ROLE_NAME);
 
 
-- Create table
create table USER_ROLE
(
  ID      BIGINT not null,
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);
--  
alter table USER_ROLE
  add constraint USER_ROLE_PK primary key (ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references USER (USER_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references ROLE (ROLE_ID);
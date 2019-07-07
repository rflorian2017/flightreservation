--------------------------------------
 
insert into USER (USER_ID, USER_NAME, PASSWORD, ACTIVE)
values (2, 'user1', '$2a$10$Ou3cs.0RBicW88a/lgPcOOIJf33/bK0W9/Ek6gAwh/p0X8DKCqM7y', 1);
 
insert into USER (USER_ID, USER_NAME, PASSWORD, ACTIVE)
values (1, 'admin1', '$2a$10$Ou3cs.0RBicW88a/lgPcOOIJf33/bK0W9/Ek6gAwh/p0X8DKCqM7y', 1);
 
---
 
insert into ROLE (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
 
insert into ROLE (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 1, 2);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);
---
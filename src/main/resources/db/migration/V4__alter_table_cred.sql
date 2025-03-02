alter table hards.credentials drop column group_id;
alter table hards.credentials drop column user_id;
alter table hards.credentials rename column c_login to login;
alter table hards.credentials rename column c_password to password;
alter table hards.credentials add column sender varchar(250);
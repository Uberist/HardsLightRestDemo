create schema if not exists hards;

create table hards.credentials(
    id serial primary key,
    user_id int,
    c_username varchar(250),
    c_system varchar(250),
    c_login varchar(250),
    c_password varchar(250)
);

create table hards.users(
    id serial primary key,
    c_name varchar(250),
    c_password varchar(250)
);

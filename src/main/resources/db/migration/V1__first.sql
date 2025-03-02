create schema if not exists hards;

create table hards.users(
    id serial primary key,
    c_name varchar(250),
    c_password varchar(250)
);

create table hards.credentials_group(
    id serial primary key,
    name_group varchar(250)
);

create table hards.credentials(
    id serial primary key,
    user_id int,
    c_username varchar(250),
    c_login varchar(250),
    c_password varchar(250),
    group_id int,
    markbook bool,
    foreign key (user_id) references hards.users (id),
    foreign key (group_id) references hards.credentials_group (id)
);

create table hards.cred_group(
    id serial primary key,
    id_cred int,
    id_group int,
    foreign key (id_cred) references hards.credentials (id),
    foreign key (id_group) references hards.credentials_group (id)
);

create table hards.system(
    id serial primary key,
    system_name varchar(250)
);

create table hards.credentials_system(
    id serial primary key ,
    cred_id int,
    system_id int,
    foreign key (cred_id) references hards.credentials (id),
    foreign key (system_id) references hards.system (id)
);


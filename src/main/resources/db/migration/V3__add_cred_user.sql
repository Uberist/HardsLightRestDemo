create table credentials_user(
    id serial primary key,
    cred_id int,
    user_id int,
    foreign key (cred_id) references hards.credentials(id),
    foreign key (user_id) references hards.users(id)
);
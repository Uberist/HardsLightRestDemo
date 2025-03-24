create table note_groups(
    id serial primary key,
    name_group varchar(250)
);
create table note_group(
    id serial primary key,
    id_note int,
    id_group int,
    foreign key (id_note) references hards.note (id),
    foreign key (id_group) references hards.note_groups (id)
);
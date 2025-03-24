create table hards.note(
    id serial primary key,
    sender int,
    begin_date_access timestamptz,
    end_date_access timestamptz,
    time_send timestamptz
);

create table hards.data(
    id serial primary key,
    idNote int,
    time varchar(250),
    version varchar(250),
    foreign key (idNote) references hards.note (id)
);

create table hards.blocks(
    idBlock serial primary key,
    idData int,
    data varchar(5000),
    id varchar(250),
    type varchar(250),
    foreign key (idData) references hards.data (id)
);

create table hards.note_user(
    id serial primary key,
    id_note int,
    id_user int,
    accept bool,
    reject bool,
    foreign key (id_note) references hards.note (id),
    foreign key (id_user) references hards.users (id)
);



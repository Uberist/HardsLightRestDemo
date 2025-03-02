-- ALTER TABLE hards.credentials ALTER COLUMN sender TYPE INTEGER USING sender::INTEGER;
alter table hards.credentials add foreign key (sender) references hards.users(id);

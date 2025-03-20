alter table hards.credentials drop column accept;
alter table hards.credentials add column accept bool;
alter table hards.credentials add column reject bool;
alter table hards.credentials drop column begin_date_access;
alter table hards.credentials drop column end_date_access;
alter table hards.credentials drop column time_send;

alter table hards.credentials add column begin_date_access timestamptz;
alter table hards.credentials add column end_date_access timestamptz;
alter table hards.credentials add column time_send timestamptz;
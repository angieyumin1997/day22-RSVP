drop schema if exists rsvp;

create schema rsvp;

use rsvp;

create table rsvp (
    name varchar(64) not null,
    email varchar(64) not null,
    phone varchar(64) not null,
    confirmation_date date not null,
    comments varchar(64) not null,

    primary key(email)

);
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table author
(
    id        uuid DEFAULT uuid_generate_v4(),
    full_name varchar not null,
    bio       text,
    primary key (id)
);

create table reader
(
    id        uuid default uuid_generate_v4(),
    full_name varchar not null,
    address   varchar not null,
    contacts  varchar not null,
    primary key (id)
);

create table book
(
    id          uuid default uuid_generate_v4(),
    title       varchar not null,
    author_id   uuid    not null,
    description text    not null,
    genre       varchar not null,
    stock       int     not null,
    primary key (id),
    foreign key (author_id)
        references author (id)
);

create table book_order
(
    id             uuid default uuid_generate_v4(),
    reader_id      uuid     not null,
    book_id        uuid     not null,
    borrowing_date date     not null,
    period       interval not null,
    primary key (id),
    foreign key (reader_id)
        references reader (id),
    foreign key (book_id)
        references book (id)

);
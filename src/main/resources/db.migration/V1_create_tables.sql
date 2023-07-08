create table users
(
    id                   int8 not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    email        varchar(255) not null,
    birthday     varchar(255) not null,
    role         varchar(255),
    password     varchar(255) not null,
    primary key (id)
);


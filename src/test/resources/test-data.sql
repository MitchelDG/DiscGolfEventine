CREATE DATABASE eventine;

USE eventine;

CREATE TABLE event
(
    id          bigint primary key auto_increment,
    name        varchar(50) not null,
    description varchar(500),
    capacity    long,
    created_at  timestamp,
    start       datetime,
    end         datetime
);

CREATE TABLE user
(
    id       bigint primary key auto_increment,
    name     varchar(50) not null,
    email    varchar(500),
    role     varchar(5),
    password varchar(100),
    locked   boolean,
    enabled  boolean
);

CREATE TABLE comment
(
    id           bigint primary key auto_increment,
    body         varchar(500) not null,
    publisher_id bigint,
    event_id     bigint       not null,
    created_at   timestamp
);

CREATE TABLE participation
(
    id       bigint primary key auto_increment,
    user_id  bigint,
    event_id bigint

);

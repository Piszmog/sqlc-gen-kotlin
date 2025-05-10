CREATE TYPE mood AS ENUM ('happy', 'sad');

CREATE TABLE people (
    id   SERIAL primary key,
    name varchar(255) NOT NULL,
    mood mood
);
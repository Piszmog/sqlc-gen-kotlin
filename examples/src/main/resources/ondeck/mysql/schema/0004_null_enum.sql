CREATE TABLE people (
    id   SERIAL primary key,
    name varchar(255) NOT NULL,
    mood ENUM('happy', 'sad')
);
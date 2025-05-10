-- name: ListPeople :many
SELECT *
FROM people;

-- name: CreatePerson :exec
INSERT INTO people (
    name,
    mood
) VALUES (
    ?,
    ?
);
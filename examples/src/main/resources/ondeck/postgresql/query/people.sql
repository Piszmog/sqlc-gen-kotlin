-- name: ListPeople :many
SELECT *
FROM people;

-- name: CreatePerson :one
INSERT INTO people (
    name,
    mood
) VALUES (
    $1,
    $2
) RETURNING id;
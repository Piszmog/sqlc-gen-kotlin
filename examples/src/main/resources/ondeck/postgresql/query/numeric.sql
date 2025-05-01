-- name: ListCounts :many
SELECT *
FROM counts;

-- name: CreateCounts :one
INSERT INTO counts (
    slug,
    count,
    increments
) VALUES (
$1,
$2,
$3
) RETURNING *;
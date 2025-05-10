-- name: CreateCheckingAccount :one
INSERT INTO checking_accounts (deposit_amount, deposit_rate)
VALUES ($1, $2) RETURNING *;

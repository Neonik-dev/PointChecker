--liquibase formatted sql

--changeset g.liseychikov:5_create_confirmation_token_table
CREATE TABLE IF NOT EXISTS confirmation_tokens
(
    id                  SERIAL            PRIMARY KEY,
    token               VARCHAR(100)      UNIQUE                        NOT NULL,
    user_id             INTEGER           REFERENCES users(id)          NOT NULL,
    expiration_date     TIMESTAMP WITHOUT TIME ZONE                     NOT NULL
);

--liquibase formatted sql

--changeset g.liseychikov:2_create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id			    SERIAL                          PRIMARY KEY,
    email           VARCHAR(255)                    UNIQUE              NOT NULL,
    password        VARCHAR(255)                                        NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE     default now()       NOT NULL
);

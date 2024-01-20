--liquibase formatted sql

--changeset g.liseychikov:6_add_verified_column
ALTER TABLE users ADD COLUMN verified BOOLEAN NOT NULL DEFAULT FALSE;
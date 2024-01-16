--liquibase formatted sql

--changeset g.liseychikov:3_add_column_user_id_in_points_table.sql
ALTER TABLE points ADD COLUMN user_id   INTEGER   REFERENCES users(id)    NOT NULL;
ALTER TABLE points ADD COLUMN created_at    TIMESTAMP WITHOUT TIME ZONE     default now()       NOT NULL;

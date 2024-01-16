--liquibase formatted sql

--changeset g.liseychikov:1_create_points_table
CREATE TABLE IF NOT EXISTS points
(
    id			SERIAL            PRIMARY KEY,
    x           float8            NOT NULL,
    y          	integer           NOT NULL,
    r           float8            NOT NULL,
    is_hit      boolean           NOT NULL
);

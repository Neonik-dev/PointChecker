--liquibase formatted sql

--changeset g.liseychikov:4_alter_column_coordinates_in_points_table
ALTER TABLE points ALTER COLUMN x TYPE double precision;
ALTER TABLE points ALTER COLUMN y TYPE double precision;
ALTER TABLE points ALTER COLUMN r TYPE double precision;

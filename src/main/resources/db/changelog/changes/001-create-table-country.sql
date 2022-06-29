--liquibase formatted sql

--changeset reinis:1

CREATE TABLE country
(
    name         VARCHAR(255) NOT NULL,
    capital      VARCHAR(255),
    population   BIGINT,
    area         INTEGER,
    last_updated TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_country PRIMARY KEY (name)
);
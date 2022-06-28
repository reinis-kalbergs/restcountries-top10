--liquibase formatted sql

--changeset reinis:1

CREATE TABLE country
(
    name       VARCHAR(255) NOT NULL,
    capital    VARCHAR(255) NOT NULL,
    population BIGINT       NOT NULL,
    area       INT          NOT NULL,
    CONSTRAINT country_id PRIMARY KEY (name)
);

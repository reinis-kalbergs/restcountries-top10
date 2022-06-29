--liquibase formatted sql

--changeset reinis:2

CREATE TABLE currency
(
    code   VARCHAR(255) PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    symbol VARCHAR(20)  NOT NULL
);

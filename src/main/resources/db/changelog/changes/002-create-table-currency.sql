--liquibase formatted sql

--changeset reinis:2

CREATE TABLE currency
(
    code   VARCHAR(255) PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    symbol VARCHAR(20)  NOT NULL
);
CREATE TABLE country_currencies
(
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_country_currencies PRIMARY KEY (code, name),
    CONSTRAINT fk_coucur_on_country_in_database_v2 FOREIGN KEY (name) REFERENCES country (name),
    CONSTRAINT fk_coucur_on_currency FOREIGN KEY (code) REFERENCES currency (code)
);

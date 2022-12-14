# isa-transfuzija


psql -U postgres
1234

CREATE DATABASE isaDB WITH ENCODING 'UTF8' LC_COLLATE='English_United States' LC_CTYPE='English_United States';
\c isadb
CREATE SCHEMA IF NOT EXISTS ISA;

CREATE TABLE users(
    id  SERIAL PRIMARY KEY NOT NULL,
    email   TEXT NOT NULL,
    password    TEXT    NOT NULL
);

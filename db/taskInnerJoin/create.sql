CREATE TABLE library (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
	genre VARCHAR(255)
);

CREATE TABLE author(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    library_id INT REFERENCES library(id) UNIQUE
);
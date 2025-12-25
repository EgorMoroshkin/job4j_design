CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    seria INT,
    passwords_id INT
);

CREATE TABLE passwords(
    id SERIAL PRIMARY KEY,
    password INT REFERENCES users(passwords_id) UNIQUE
);
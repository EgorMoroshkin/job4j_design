CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT,
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT
     roles_id INT REFERENCES roles(id)
);

CREATE TABLE rules (
    id SERIAL PRIMARY KEY,
    name TEXT,
);

--вспомогательная таблица для связи many-to-many roles и rules
CREATE TABLE roles_rules(
    id SERIAL PRIMARY KEY,
    roles_id INT REFERENCES roles(id),
    rules_id INT REFERENCES rules(id)
);

CREATE TABLE сategories (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE states (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    name TEXT
    user_id INT REFERENCES user(id)
    сategories_id INT REFERENCES сategories(id)
    states_id INT REFERENCES states(id)
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

CREATE TABLE attachs (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

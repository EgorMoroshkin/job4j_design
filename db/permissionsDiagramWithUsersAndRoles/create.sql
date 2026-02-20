
create TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT
     roles_id INT REFERENCES roles(id)
);

create TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT,
);

-- вспомогательная таблица для связи many-to-many roles и rules
create TABLE roles_rules(
    id SERIAL PRIMARY KEY,
    roles_id INT REFERENCES roles(id),
    rules_id INT REFERENCES rules(id)
);

create TABLE rules (
    id SERIAL PRIMARY KEY,
    name TEXT,
);

create TABLE items (
    id SERIAL PRIMARY KEY,
    name TEXT
    user_id INT REFERENCES user(id)
);

create TABLE comments (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

create TABLE attachs (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

create TABLE сategories (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

create TABLE states (
    id SERIAL PRIMARY KEY,
    name TEXT
    items_id INT REFERENCES items(id)
);

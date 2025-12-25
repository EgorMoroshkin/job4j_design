CREATE TABLE department(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE workers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES department(id)
);

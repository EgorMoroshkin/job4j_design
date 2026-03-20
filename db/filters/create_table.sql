CREATE TABLE product
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
	type_id  INT,
	expired_date  DATE,
    price DECIMAL(10, 2)
);

CREATE TABLE type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
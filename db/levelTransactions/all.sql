CREATE TABLE cars
(
    id       SERIAL PRIMARY KEY,
    brand    VARCHAR(50),
    model    VARCHAR(50),
    year     INTEGER,
    color    VARCHAR(30),
    price    INTEGER,
    quantity INTEGER DEFAULT 0
);

INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Toyota', 'Camry', 2022, 'Silver', 3500000, 5);

INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Toyota', 'Corolla', 2023, 'White', 2800000, 8);

INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Hyundai', 'Sonata', 2022, 'Black', 3200000, 3);

INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Hyundai', 'Elantra', 2023, 'Blue', 2500000, 7);

INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Kia', 'K5', 2022, 'Red', 3300000, 4);



SELECT SUM(quantity) FROM cars;
UPDATE cars SET quantity = 50 WHERE model = 'K5';
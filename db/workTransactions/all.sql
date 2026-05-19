SELECT * FROM cars;

BEGIN TRANSACTION;

SAVEPOINT firstDpop;

DELETE FROM cars WHERE brand = 'Toyota';
INSERT INTO cars (brand, model, year, color, price, quantity)
VALUES ('Toyota', 'Prado', 2026, 'Black', 7777777, 1);

SELECT * FROM cars;

ROLLBACK TO firstDpop;

SAVEPOINT secondCharge;

DROP TABLE cars;

SELECT * FROM cars;

ROLLBACK TO secondCharge;

COMMIT;
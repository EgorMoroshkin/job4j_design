CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

--Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар
--(нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)

CREATE OR REPLACE FUNCTION add_tax_after_insert()
RETURNS trigger AS
$$
    BEGIN
        UPDATE products p
        SET price = p.price * 1.13
        FROM inserted
        WHERE p.id = inserted.id;

        RETURN NULL;
    END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_add_tax_after_insert
    AFTER INSERT ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE FUNCTION add_tax_after_insert();

INSERT INTO products (name, producer, count, price)
VALUES ('Молоко', 'Простоквашино', 16, 100);

SELECT * FROM products;

DROP TRIGGER trigger_add_tax_after_insert ON products;

INSERT INTO products (name, producer, count, price)
VALUES ('Кефир', 'Домик в деревне', 100, 150);

-- Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
--Здесь используем row уровень.

CREATE OR REPLACE FUNCTION add_tax_before_insert()
RETURNS trigger AS
$$
    BEGIN
        NEW.price = NEW.price * 1.13;
        RETURN NEW;
    END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_add_tax_before_insert
    BEFORE INSERT ON products
    FOR EACH ROW
    EXECUTE FUNCTION add_tax_before_insert();

INSERT INTO products (name, producer, count, price)
VALUES ('Йогурт', 'Село зеленое', 5, 50);

SELECT * FROM products;

DROP TRIGGER trigger_add_tax_before_insert ON products;

INSERT INTO products (name, producer, count, price)
VALUES ('Творог', 'Домик в деревне', 80, 200);

--  Создайте таблицу, нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
-- будет заносить имя, цену и текущую дату в таблицу history_of_price.

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION add_history_of_price_before_insert()
RETURNS trigger AS
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
		VALUES (NEW.name, NEW.price, NOW());
        RETURN NEW;
    END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_add_history_of_price_before_insert
    AFTER INSERT ON products
    FOR EACH ROW
    EXECUTE FUNCTION add_history_of_price_before_insert();


INSERT INTO products (name, producer, count, price)
VALUES ('Ряженка', 'Простоквашино', 20, 70);

SELECT * FROM products;
SELECT * FROM history_of_price;

DROP TRIGGER trigger_add_history_of_price_before_insert ON products;

INSERT INTO products (name, producer, count, price)
VALUES ('Сметана', 'Простоквашино', 10, 190);



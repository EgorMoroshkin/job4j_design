DELETE FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

INSERT INTO products (name, producer, count, price)
VALUES ('Молоко', 'Простоквашино', 16, 100);

INSERT INTO products (name, producer, count, price)
VALUES ('Кефир', 'Домик в деревне', 100, 150);

INSERT INTO products (name, producer, count, price)
VALUES ('Йогурт', 'Село зеленое', 5, 50);

INSERT INTO products (name, producer, count, price)
VALUES ('Творог', 'Домик в деревне', 80, 200);

SELECT * FROM products;

CREATE
OR REPLACE PROCEDURE delete_data(u_id integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        DELETE FROM products
		WHERE id = u_id;
    END
$$;

CALL delete_data(3);

------------------------------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION f_delete_data(i_id integer, i_price integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
    BEGIN
        DELETE FROM products
        WHERE id = i_id AND price > i_price;
    END;
$$;

SELECT * FROM products;

SELECT f_delete_data(2, 140);
SELECT f_delete_data(3, 60);
------------------------------------------------------------------------------------------
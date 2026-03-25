-- 1. Создать таблицы

CREATE TABLE car_bodies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_engines
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_transmissions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cars
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
	body_id INT REFERENCES car_bodies (id),
	engine_id INT REFERENCES car_engines (id),
	transmission_id INT REFERENCES car_transmissions (id)
);

SELECT * FROM car_bodies;
SELECT * FROM car_engines;
SELECT * FROM car_transmissions;
SELECT * FROM cars;

INSERT INTO car_bodies(name)
VALUES ('седан');
INSERT INTO car_bodies(name)
VALUES ('хэтчбек');
INSERT INTO car_bodies(name)
VALUES ('пикап');
INSERT INTO car_bodies(name)
VALUES ('универсал');
INSERT INTO car_bodies(name)
VALUES ('лимузин');

INSERT INTO car_engines(name)
VALUES ('бензин');
INSERT INTO car_engines(name)
VALUES ('дизель');
INSERT INTO car_engines(name)
VALUES ('электро');
INSERT INTO car_engines(name)
VALUES ('гибрид');

INSERT INTO car_transmissions(name)
VALUES ('авто');
INSERT INTO car_transmissions(name)
VALUES ('ручная');
INSERT INTO car_transmissions(name)
VALUES ('робот');

-- Вставляем машины с полным набором деталей
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Toyota Camry', 1, 1, 1),      -- седан, бензин, авто
    ('BMW X5', 4, 2, 1),            -- универсал, дизель, авто
    ('Volkswagen Golf', 2, 1, 2),   -- хэтчбек, бензин, ручная
    ('Tesla Model 3', 1, 3, 1);     -- седан, электро, авто

-- Машины без кузова (body_id = NULL)
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Nissan Leaf', NULL, 3, 1),    -- электро, авто
    ('Mercedes Sprinter', NULL, 2, 2); -- дизель, ручная

-- Машины без двигателя (engine_id = NULL)
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Прицеп', 3, NULL, 2),         -- пикап, ручная
    ('Автодом', 4, NULL, 1);        -- универсал, авто

-- Машины без коробки передач (transmission_id = NULL)
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Электрокар для гольфа', 2, 3, NULL), -- хэтчбек, электро
    ('Самоделка', 3, 1, NULL);            -- пикап, бензин

-- Машины без кузова и двигателя
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Трейлер', NULL, NULL, 2);     -- ручная

-- Машины без кузова и коробки
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Гоночный болид', NULL, 1, NULL); -- бензин

-- Машины без двигателя и коробки
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Выставочный экземпляр', 1, NULL, NULL); -- седан

-- Машины вообще без деталей
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
    ('Концепт-кар', NULL, NULL, NULL);

-- 2. Создать SQL запросы:
SELECT c.id, c.name AS car_name, b.name AS body_name, e.name AS engine_name, t.name AS transmission_name
FROM cars c
LEFT JOIN car_bodies b ON c.body_id = b.id
LEFT JOIN car_engines e ON c.engine_id = e.id
LEFT JOIN car_transmissions t ON c.transmission_id = t.id;

SELECT * FROM car_bodies b
         LEFT JOIN cars c ON b.id = c.body_id
WHERE c.id IS NULL;

SELECT * FROM car_engines e
         LEFT JOIN cars c ON e.id = c.engine_id
WHERE c.id IS NULL;

SELECT * FROM car_transmissions t
         LEFT JOIN cars c ON t.id = c.transmission_id
WHERE c.id IS NULL;
-- 1. Создать таблицы и заполнить их начальными данными
CREATE TABLE departments
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
	departments_id INT REFERENCES departments (id)
);

INSERT INTO departments(name)
VALUES ('Строительство');
INSERT INTO departments(name)
VALUES ('Логистика');
INSERT INTO departments(name)
VALUES ('HR');
INSERT INTO departments(name)
VALUES ('Продажи');


INSERT INTO employees(name, departments_id)
VALUES ('Иванов', 1);
INSERT INTO employees(name, departments_id)
VALUES ('Петров', null);
INSERT INTO employees(name, departments_id)
VALUES ('Сидоров', 3);
INSERT INTO employees(name, departments_id)
VALUES ('Зуб', null);
INSERT INTO employees(name, departments_id)
VALUES ('Заяц', null);
INSERT INTO employees(name, departments_id)
VALUES ('Пашинян', 1);
INSERT INTO employees(name, departments_id)
VALUES ('Алиев', 2);
INSERT INTO employees(name, departments_id)
VALUES ('Мухтаров', 3);
INSERT INTO employees(name, departments_id)
VALUES ('Макаров', 3);

SELECT * FROM employees
SELECT * FROM departments

-- 2. Выполнить запросы с left, right, full, cross соединениями
SELECT * FROM departments d
         LEFT JOIN employees e ON d.id = e.departments_id;

SELECT * FROM departments d
         RIGHT JOIN employees e ON d.id = e.departments_id;

SELECT * FROM departments d
         FULL JOIN employees e ON d.id = e.departments_id;

SELECT * FROM departments d
         CROSS JOIN employees e;

-- 3. Используя left join найти департаменты, у которых нет работников
SELECT * FROM departments d
         LEFT JOIN employees e ON d.id = e.departments_id
WHERE e.id IS NULL;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат
-- (порядок вывода колонок в эти запросах также должен быть идентичный).
SELECT * FROM departments d
         LEFT JOIN employees e ON d.id = e.departments_id;

SELECT d.id, d.name, e.id, e.name, e.departments_id FROM employees e
         RIGHT JOIN departments d ON d.id = e.departments_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары. Исключите дублирование пар вида Вася-Маша и Маша-Вася.
CREATE TABLE teens
(
    name VARCHAR(255) UNIQUE,
	gender VARCHAR(255)

);

INSERT INTO teens(name, gender)
VALUES ('Вася', 'male');
INSERT INTO teens(name, gender)
VALUES ('Петя', 'male');
INSERT INTO teens(name, gender)
VALUES ('Коля', 'male');
INSERT INTO teens(name, gender)
VALUES ('Маша', 'female');
INSERT INTO teens(name, gender)
VALUES ('Ира', 'female');
INSERT INTO teens(name, gender)
VALUES ('Аня', 'female');

SELECT * FROM teens;

SELECT n1.name, n2.name FROM teens n1
         CROSS JOIN teens n2
WHERE n1.gender != n2.gender
AND n1.name < n2.name
ORDER BY n1.name, n2.name;
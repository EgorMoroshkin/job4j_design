CREATE TABLE students
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO students (name)
VALUES ('Иван Иванов');
INSERT INTO students (name)
VALUES ('Петр Петров');

CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO authors (name)
VALUES ('Александр Пушкин');
INSERT INTO authors (name)
VALUES ('Николай Гоголь');


CREATE TABLE books
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);

SELECT * FROM orders;

CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    active BOOLEAN DEFAULT true,
    book_id INTEGER REFERENCES books (id),
    student_id INTEGER REFERENCES students (id)
);

INSERT INTO orders (book_id, student_id)
VALUES (1, 1);
INSERT INTO orders (book_id, student_id)
VALUES (3, 1);
INSERT INTO orders (book_id, student_id)
VALUES (5, 2);
INSERT INTO orders (book_id, student_id)
VALUES (4, 1);
INSERT INTO orders (book_id, student_id)
VALUES (2, 2);


SELECT s.name, COUNT(a.name), a.name
FROM students AS s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
GROUP BY (s.name, a.name)
HAVING COUNT(a.name) >= 2;

-- Создать представление

CREATE VIEW show_students_read_author_pushkin
AS
SELECT
    s.name AS student,
    b.name AS book,
    a.name AS author,
    o.active
FROM students s
JOIN orders o ON s.id = o.student_id
JOIN books b ON o.book_id = b.id
JOIN authors a ON b.author_id = a.id
WHERE a.name = 'Александр Пушкин'
ORDER BY s.name;

SELECT * FROM show_students_read_author_pushkin;

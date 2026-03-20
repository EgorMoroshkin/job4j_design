-- Task #1
SELECT ss.*, s.name
FROM product ss
JOIN type s ON ss.type_id = s.id
WHERE s.name = 'СЫР';

-- Task #2
SELECT * FROM product
WHERE name LIKE '%Мороженое%';

-- Task #3
SELECT * FROM product
WHERE expired_date < CURRENT_DATE;

-- Task #4
SELECT * FROM product
WHERE price = (SELECT MAX(price) FROM product);

-- Task #5 -- нужно дописать
SELECT s.name, COUNT(ss.id) AS product_count
FROM type s
LEFT JOIN product ss ON s.id = ss.type_id
GROUP BY s.name
ORDER BY product_count DESC;

-- Task #6
SELECT ss.*, s.name
FROM product ss
JOIN type s ON ss.type_id = s.id
WHERE s.name IN ('СЫР', 'МОЛОКО');

-- Task #7
SELECT s.name, COUNT(ss.id) AS product_count
FROM type s
LEFT JOIN product ss ON s.id = ss.type_id
GROUP BY s.name
HAVING COUNT(ss.id) < 10
ORDER BY product_count DESC;

-- Task #8
SELECT ss.*, s.name
FROM product ss
JOIN type s ON ss.type_id = s.id;
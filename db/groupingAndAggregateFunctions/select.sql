-- Task #3
SELECT name AS Имя, AVG(price) AS "Средняя цена устройства"
FROM devices
GROUP BY name;

-- Task #4
SELECT ss.name AS Имя, AVG(s.price) AS "Средняя цена устройств"
FROM people AS ss
JOIN devices_people AS dp ON ss.id = dp.people_id
JOIN devices AS s ON dp.device_id = s.id
GROUP BY ss.name;

-- Task #5
SELECT ss.name AS "Имя человека", AVG(s.price) AS "Средняя цена устройств"
FROM people AS ss
JOIN devices_people AS dp ON ss.id = dp.people_id
JOIN devices AS s ON dp.device_id = s.id
GROUP BY ss.id, ss.name
HAVING AVG(s.price) > 5000;
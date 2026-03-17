INSERT INTO devices (name, price) VALUES
-- iPhone 5 (разброс от 3000 до 6000)
('iPhone 5', 3000.00),
('iPhone 5', 3800.00),
('iPhone 5', 4500.00),
('iPhone 5', 5200.00),
('iPhone 5', 6000.00),

-- iPhone 6 (разброс от 4500 до 8000)
('iPhone 6', 4500.00),
('iPhone 6', 5300.00),
('iPhone 6', 6100.00),
('iPhone 6', 7000.00),
('iPhone 6', 8000.00),

-- iPhone 7 (разброс от 6000 до 10000)
('iPhone 7', 6000.00),
('iPhone 7', 7200.00),
('iPhone 7', 8200.00),
('iPhone 7', 9100.00),
('iPhone 7', 10000.00);

INSERT INTO people (name) VALUES
('Иван Петров'),
('Мария Иванова'),
('Алексей Сидоров');

INSERT INTO devices_people (device_id, people_id) VALUES
-- Устройства Ивана (people_id = 1)
(1, 1),  -- iPhone 5 (3000)
(2, 1),  -- iPhone 5 (3800)
(6, 1),  -- iPhone 6 (4500)
(7, 1),  -- iPhone 6 (5300)
(11, 1), -- iPhone 7 (6000)
(12, 1), -- iPhone 7 (7200)

-- Устройства Марии (people_id = 2)
(3, 2),  -- iPhone 5 (4500)
(4, 2),  -- iPhone 5 (5200)
(8, 2),  -- iPhone 6 (6100)
(9, 2),  -- iPhone 6 (7000)
(13, 2), -- iPhone 7 (8200)
(14, 2), -- iPhone 7 (9100)

-- Устройства Алексея (people_id = 3)
(5, 3),  -- iPhone 5 (6000)
(10, 3), -- iPhone 6 (8000)
(15, 3), -- iPhone 7 (10000)
(1, 3),  -- iPhone 5 (3000)
(6, 3),  -- iPhone 6 (4500)
(11, 3); -- iPhone 7 (6000)

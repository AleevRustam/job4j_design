CREATE TABLE type(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    type_id      INT REFERENCES type(id),
    expired_date DATE,
    price        FLOAT
);

INSERT INTO type (name) VALUES
                            ('МОЛОКО'),
                            ('СЫР'),
                            ('ОВОЩИ_ФРУКТЫ'),
                            ('МЯСО'),
                            ('НАПИТКИ'),
                            ('ЗАКУСКИ'),
                            ('МОРЕПРОДУКТЫ'),
                            ('МОРОЖЕНОЕ');
INSERT INTO product (name, type_id, expired_date, price) VALUES
                                                             ('Молоко Буренка', 1, '2024-07-20', 150),
                                                             ('Молоко Простоквашино', 1, '2024-06-20', 250),
                                                             ('Сыр Сливочный', 2, '2024-08-15', 500),
                                                             ('Сыр Российский', 2, '2024-09-15', 800),
                                                             ('Яблоко', 3, '2024-09-25', 50),
                                                             ('Банан', 3, '2024-09-22', 30),
                                                             ('Морковь', 3, '2024-09-28', 20),
                                                             ('Броколи', 3, '2023-07-30', 170),
                                                             ('Куриная грудка', 4, '2024-07-30', 400),
                                                             ('Beef Steak', 4, '2024-10-20', 800),
                                                             ('Сок Апельсиновый', 5, '2025-07-25', 100),
                                                             ('Сок яблочный', 5, '2025-01-01', 100),
                                                             ('Чипсы Lays', 6, '2026-02-15', 300),
                                                             ('Чипсы картофельные', 6, '2025-01-01', 120),
                                                             ('Лосось', 7, '2024-07-29', 800),
                                                             ('Креветки', 7, '2025-07-22', 700),
                                                             ('Мороженое пломбир', 8, '2025-07-22', 60),
                                                             ('Мороженое Соло', 8, '2024-07-10', 70),
                                                             ('Мороженое Русский Холод', 8, '2025-07-22', 115);

SELECT * FROM product p
    JOIN public.type t ON t.id = p.type_id
         WHERE t.name = 'СЫР';
SELECT * FROM product p
    JOIN public.type t ON t.id = p.type_id
         WHERE p.name LIKE '%Мороженое%';
SELECT * FROM product p
    JOIN public.type t ON t.id = p.type_id
         WHERE expired_date < CURRENT_DATE;
SELECT * FROM product p
    JOIN public.type t ON t.id = p.type_id
         WHERE price = (SELECT MAX(price) FROM product);
SELECT t.name AS имя_типа, COUNT(p.id) AS количество FROM type t
    JOIN product p ON t.id = p.type_id GROUP BY t.name;
SELECT * FROM product p
                  JOIN type t ON t.id = p.type_id
WHERE t.name IN ('СЫР', 'МОЛОКО');
SELECT t.name AS тип, COUNT(p.id) AS количество FROM product p
                  JOIN type t ON t.id = p.type_id
                                                GROUP BY t.name
                                                HAVING COUNT(p.id) < 10;
SELECT p.name AS Продукт, t.name AS Тип FROM product p JOIN type t on t.id = p.type_id;


CREATE TABLE products(
                         id    SERIAL PRIMARY KEY,
                         name  varchar(50),
                         producer varchar(50),
                         count integer default 0,
                         price integer
);

INSERT INTO products(name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products(name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
INSERT INTO products(name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

BEGIN;
INSERT INTO products(name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
COMMIT;
SELECT * FROM products;
BEGIN;
DELETE FROM products;
DROP TABLE products;
ROLLBACK;
SELECT * FROM products;
BEGIN;
INSERT INTO products(name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);
SAVEPOINT first_savepoint;
DELETE FROM products WHERE price = 115;
UPDATE products SET price = 75 WHERE name = 'product_1';
SELECT * FROM products;
ROLLBACK TO first_savepoint;
SELECT * FROM products;
COMMIT;
BEGIN;
SAVEPOINT second_savepoint;
SELECT * FROM products;
INSERT INTO products(name, producer, count, price) VALUES ('product_6', 'producer_6', 22, 66);
INSERT INTO products(name, producer, count, price) VALUES ('product_7', 'producer_7', 22, 115);
SAVEPOINT third_savepoint;
DELETE FROM products WHERE price = 115;
SELECT * FROM products;
DELETE FROM products;
SELECT * FROM products;
ROLLBACK TO third_savepoint;
SELECT * FROM products;

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

INSERT INTO products (name, category, price, stock)
VALUES
    ('Laptop', 'Electronics', 1000.00, 50),
    ('Smartphone', 'Electronics', 600.00, 150),
    ('Tablet', 'Electronics', 400.00, 80),
    ('Headphones', 'Accessories', 100.00, 200),
    ('Charger', 'Accessories', 20.00, 300);

--Terminal1
BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
UPDATE products SET stock = stock - 10 WHERE name = 'Laptop';

--Terminal2
BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
UPDATE products SET stock = stock - 5 WHERE name = 'Laptop';

--Terminal1
COMMIT;
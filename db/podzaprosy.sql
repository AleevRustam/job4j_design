CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES ('Иван', 'Иванов', 28, 'Россия');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Алексей', 'Смирнов', 35, 'Россия');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Мария', 'Кузнецова', 22, 'Казахстан');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Ольга', 'Попова', 30, 'Беларусь');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Дмитрий', 'Петров', 40, 'Молдова');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Анна', 'Соколова', 25, 'Россия');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Екатерина', 'Лебедева', 32, 'Казахстан');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Андрей', 'Козлов', 29, 'Россия');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Наталья', 'Новикова', 27, 'Молдова');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Владимир', 'Морозов', 45, 'Россия');

SELECT * FROM customers WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES (1000, 1);
INSERT INTO orders (amount, customer_id) VALUES (2000, 5);
INSERT INTO orders (amount, customer_id) VALUES (1500, 3);
INSERT INTO orders (amount, customer_id) VALUES (3000, 7);
INSERT INTO orders (amount, customer_id) VALUES (2500, 2);
INSERT INTO orders (amount, customer_id) VALUES (4000, 9);
INSERT INTO orders (amount, customer_id) VALUES (3500, 6);
INSERT INTO orders (amount, customer_id) VALUES (5000, 4);
INSERT INTO orders (amount, customer_id) VALUES (4500, 8);
INSERT INTO orders (amount, customer_id) VALUES (6000, null);
INSERT INTO orders (amount, customer_id) VALUES (7000, 1);
INSERT INTO orders (amount, customer_id) VALUES (8000, 3);
INSERT INTO orders (amount, customer_id) VALUES (9000, 2);
INSERT INTO orders (amount, customer_id) VALUES (10000, 6);
INSERT INTO orders (amount, customer_id) VALUES (11000, 7);


SELECT *
FROM customers
WHERE  id NOT IN (SELECT customer_id FROM orders WHERE customer_id IS NOT NULL );
CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name VARCHAR(50),
    producer     VARCHAR(50),
    count INTEGER DEFAULT 0,
    price    INTEGER
);

INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

CREATE
    OR REPLACE FUNCTION apply_tax_after_insert()
    RETURNS trigger AS
    $$
        BEGIN
            UPDATE products
            SET price = price + price * 0.2
            WHERE id IN (SELECT id FROM inserted);
            RETURN new;
        END;
    $$
LANGUAGE 'plpgsql';

CREATE TRIGGER apply_tax_after_insert_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS
        inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE apply_tax_after_insert();

INSERT INTO products (name, producer, count, price) VALUES ('product_4', 'producer_4', 40, 100);

DROP TRIGGER apply_tax_after_insert_trigger ON products;

CREATE
    OR REPLACE FUNCTION apply_tax_before_inset()
    RETURNS trigger AS
    $$
        BEGIN
            NEW.price = NEW.price + NEW.price * 0.2;
            RETURN NEW;
        END;
    $$
LANGUAGE 'plpgsql';

CREATE TRIGGER apply_tax_before_insert_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE apply_tax_before_inset();

INSERT INTO products (name, producer, count, price) VALUES ('product_11', 'producer_11', 40, 100);

DROP TRIGGER apply_tax_before_insert_trigger ON products;

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

CREATE
    OR REPLACE FUNCTION fill_price_history()
    RETURNS TRIGGER AS
    $$
        BEGIN
            INSERT INTO history_of_price(name, price, date)
            VALUES (NEW.name, NEW.price, current_timestamp);
            RETURN NEW;
        END;
    $$
LANGUAGE 'plpgsql';

CREATE TRIGGER fill_price_history_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE fill_price_history();

INSERT INTO products (name, producer, count, price) VALUES ('product_12', 'producer_12', 50, 1000);
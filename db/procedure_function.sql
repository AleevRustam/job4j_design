create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);
CREATE
OR REPLACE PROCEDURE insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
LANGUAGE 'plpgsql'
AS $$
    BEGIN
        INSERT INTO products (name, producer, count, price)
        VALUES (i_name, prod, i_count, i_price);
    END
    $$;

CALL insert_data('product_2', 'producer_2', 15, 32);

CREATE
    OR REPLACE PROCEDURE update_data(u_count integer, tax float, u_id integer)
    LANGUAGE 'plpgsql'
AS $$
BEGIN
    IF u_count > 0 THEN
        UPDATE products
        SET count = count - u_count
        WHERE id = u_id;
    END IF ;
    IF
        tax > 0 THEN
        UPDATE products
        SET price = price + price * tax;
    END IF ;
END;
$$;

CALL update_data(10, 0, 1);
CALL insert_data('product_1', 'producer_1', 3, 50);
CALL insert_data('product_3', 'producer_3', 8, 115);
CALL update_data(0, 0.2, 0);

ALTER PROCEDURE update_data(u_count integer, tax float, u_id integer) rename to update;
DROP PROCEDURE update(u_count integer, tax float, u_id integer);

DELETE FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

CREATE
OR REPLACE FUNCTION f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
    $$
        BEGIN
            INSERT INTO products (name, producer, count, price)
            VALUES (i_name, prod, i_count, i_price);
        END;
    $$;

SELECT f_insert_data('product_1', 'producer_1', 25, 50);

CREATE
OR REPLACE FUNCTION f_update_data(u_count integer, tax float, u_id integer)
RETURNS integer
LANGUAGE 'plpgsql'
AS
    $$
        DECLARE
            result integer;
        BEGIN
           IF u_count > 0 THEN
               UPDATE products
               SET count = count - u_count
               WHERE id = u_id;
               SELECT INTO result count
               FROM products
               WHERE id = u_id;
           END IF;
           IF tax > 0 THEN
               UPDATE products
               SET price = price + price * tax;
               SELECT INTO result sum(price)
               FROM products;
           END IF;
           RETURN result;
        END;
    $$;

SELECT f_update_data(10, 0, 1);

SELECT f_insert_data('product_2', 'producer_2', 15, 32);
SELECT f_insert_data('product_3', 'producer_3', 8, 115);

SELECT f_update_data(0, 0.2, 0);

CREATE
OR REPLACE FUNCTION f_delete_product_by_id(p_id integer)
RETURNS boolean
LANGUAGE 'plpgsql'
AS
    $$
        DECLARE
            result boolean;
        BEGIN
            DELETE FROM products
            WHERE id = p_id;
            IF FOUND THEN
                result = TRUE;
            ELSE
                result = FALSE;
            END IF;
            RETURN result;
        END;
    $$;

SELECT f_delete_product_by_id(2);
SELECT f_delete_product_by_id(5);

CREATE
OR REPLACE PROCEDURE delete_product(p_id integer)
LANGUAGE 'plpgsql'
AS
    $$
        BEGIN
            DELETE FROM products WHERE id = p_id;
        END;
    $$;

CALL delete_product(3);

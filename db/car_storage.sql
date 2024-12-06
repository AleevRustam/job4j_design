CREATE TABLE car_bodies(
    id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE car_engines(
    id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE car_transmissions(
    id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    name text,
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES ('SEDAN'), ('HATCHBACK'), ('STATION WAGON'), ('CABRIO'), ('LIMO');
INSERT INTO car_engines(name) VALUES ('DIESEL'), ('GASOLINE'), ('ELECTRO');
INSERT INTO car_engines(name) VALUES ('STEAM');
INSERT INTO car_transmissions(name) VALUES ('MANUAL'), ('AUTOMATIC');
INSERT INTO car_transmissions(name) VALUES ('DIRECT');
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('FERRARI', 4, 2, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('AUDI', 1, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('BMW', 3, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('TESLA', 2, 3, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('Car1', null, 3, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('Car2', 2, null, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) values ('Car3', 2, 3, null);

SELECT c.id, c.name, cb.name, ce.name, ct.name FROM cars c
LEFT JOIN car_bodies cb ON cb.id = c.body_id
LEFT JOIN car_engines ce ON ce.id = c.engine_id
LEFT JOIN car_transmissions ct ON ct.id = c.transmission_id;

SELECT cb.name AS body_name
FROM car_bodies cb
    LEFT JOIN cars c ON cb.id = c.body_id
WHERE c.id is null;

SELECT ce.name AS engine_name
FROM car_engines ce
    LEFT JOIN cars c ON ce.id = c.engine_id
WHERE c.id is null;

SELECT ct.name AS transmission_name
FROM car_transmissions ct
    LEFT JOIN cars c ON ct.id = c.transmission_id
WHERE c.id is null;


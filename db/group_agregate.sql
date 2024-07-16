create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO people(name) VALUES ('Person1'), ('Person2'), ('Person3');
INSERT INTO devices(name, price) VALUES ('Device1', 2000), ('Device2', 3000), ('Device3', 9000);
INSERT INTO devices_people(people_id, device_id) VALUES (1, 1), (1, 2);
INSERT INTO devices_people(people_id, device_id) VALUES (2, 1), (2, 3);
INSERT INTO devices_people(people_id, device_id) VALUES (3, 2), (3, 3);


SELECT AVG(price) FROM devices;

SELECT p.name, AVG(d.price)
FROM people p
         JOIN devices_people dp on p.id = dp.people_id
         JOIN devices d on d.id = dp.device_id
GROUP BY p.name;

SELECT p.name, AVG(d.price)
FROM people p
         JOIN devices_people dp on p.id = dp.people_id
         JOIN devices d on d.id = dp.device_id
GROUP BY p.name
HAVING AVG(d.price) > 5000;
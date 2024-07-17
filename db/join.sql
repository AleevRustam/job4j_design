create table departments(
    id   serial primary key,
    name varchar(255)
);

create table employees(
    id       serial primary key,
    name     varchar(255),
    department_id int references departments (id)
);

INSERT INTO departments(name) VALUES ('Development'), ('Production'), ('Distribution');

INSERT INTO employees(name, department_id) VALUES ('Employee1', 1);
INSERT INTO employees(name, department_id) VALUES ('Employee2', 2);
INSERT INTO employees(name, department_id) VALUES ('Employee3', 2);
INSERT INTO employees(name, department_id) VALUES ('Employee4', null);
INSERT INTO employees(name, department_id) VALUES ('Employee5', null);
INSERT INTO employees(name, department_id) VALUES ('Employee6', 1);

SELECT * FROM employees e LEFT JOIN departments d ON d.id = e.department_id;
SELECT * FROM departments d RIGHT JOIN employees e on d.id = e.department_id;
SELECT * FROM employees e FULL JOIN departments d on d.id = e.department_id;
SELECT * FROM employees e CROSS JOIN departments d;
SELECT d.name
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;
SELECT * FROM employees e
    LEFT JOIN departments d ON d.id = e.department_id;
SELECT e.id, e.name, department_id, d.id, d.name
FROM departments d
    RIGHT JOIN employees e ON d.id = e.department_id;

CREATE TABLE teens(
    id serial primary key,
    name text,
    gender text
);

INSERT INTO teens(name, gender) VALUES ('Вася', 'м');
INSERT INTO teens(name, gender) VALUES ('Петя', 'м');
INSERT INTO teens(name, gender) VALUES ('Леша', 'м');
INSERT INTO teens(name, gender) VALUES ('Маша', 'ж');
INSERT INTO teens(name, gender) VALUES ('Ира', 'ж');
INSERT INTO teens(name, gender) VALUES ('Юля', 'ж');

SELECT t1.name AS м, t2.name AS ж
FROM teens t1
         CROSS JOIN teens t2
WHERE t1.gender = 'м' AND t2.gender = 'ж';
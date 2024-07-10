CREATE TABLE levels(
	id serial primary key,
	level varchar(255) UNIQUE
);

CREATE TABLE students(
	id serial primary key,
	name varchar(255),
	levels_id int references levels(id) 
);
INSERT INTO levels(level) VALUES ('Beginner');
INSERT INTO students(name, levels_id) VALUES ('Rustam', 1);

SELECT * FROM students;

SELECT * FROM levels WHERE id IN (SELECT levels_id FROM students);
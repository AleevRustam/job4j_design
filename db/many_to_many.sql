CREATE TABLE subjects(
	id serial primary key,
	name varchar(255) UNIQUE
);

CREATE TABLE students(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE students_subjects(
	id serial primary key,
	student_id int REFERENCES students(id),
	subject_id int REFERENCES subjects(id)
);

INSERT INTO students(name) VALUES ('Rustam');
INSERT INTO students(name) VALUES ('Ivan');
INSERT INTO students(name) VALUES ('Petr');

INSERT INTO subjects(name) VALUES ('Math');
INSERT INTO subjects(name) VALUES ('Artist');
INSERT INTO subjects(name) VALUES ('Chemistry');

INSERT INTO students_subjects(student_id, subject_id) VALUES (1, 1);
INSERT INTO students_subjects(student_id, subject_id) VALUES (1, 2);
INSERT INTO students_subjects(student_id, subject_id) VALUES (1, 3);
INSERT INTO students_subjects(student_id, subject_id) VALUES (2, 1);
INSERT INTO students_subjects(student_id, subject_id) VALUES (2, 2);
INSERT INTO students_subjects(student_id, subject_id) VALUES (3, 3);


SELECT * FROM students_subjects;

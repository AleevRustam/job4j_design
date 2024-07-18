create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');

create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);

CREATE VIEW detailed_orders
AS
SELECT s.name AS student,
       b.name AS book,
       a.name AS author,
       o.id AS order,
       o.active AS order_status,
       (SELECT COUNT(*) FROM orders o WHERE o.student_id = s.id) AS total_orders_per_student,
       (SELECT COUNT(*) FROM orders o WHERE o.book_id = b.id) AS total_orders_per_book,
       (SELECT COUNT(*) FROM orders o
           JOIN books b ON o.book_id = b.id
                        WHERE b.author_id = a.id) AS total_orders_per_author
FROM students s
    JOIN orders o on s.id = o.student_id
    JOIN books b on o.book_id = b.id
    JOIN authors a on a.id = b.author_id ORDER BY s.name, b.name, a.name;

SELECT * FROM detailed_orders;
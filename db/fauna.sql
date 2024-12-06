create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Goldfish', 100, '1800-01-01');
insert into fauna(name, avg_age, discovery_date) values ('Tuna', 2, '2020-10-01');
insert into fauna(name, avg_age, discovery_date) values ('Salmon', 5, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values ('Shark', 10, '1700-02-01');
insert into fauna(name, avg_age, discovery_date) values ('Carp', 2, '1700-02-01');
insert into fauna(name, avg_age, discovery_date) values ('Pike', 8, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values ('Guppies', 1, '1700-02-01');
insert into fauna(name, avg_age, discovery_date) values ('Mackerel', 10, '1800-04-01');
insert into fauna(name, avg_age, discovery_date) values ('Grouper', 9, '2020-11-01');
insert into fauna(name, avg_age, discovery_date) values ('Clownfish', 20, '1800-01-01');
insert into fauna(name, avg_age, discovery_date) values ('Clownfish', 11000, '1800-01-01');

select * from fauna where name like '%fish%'


select * from fauna where fauna.avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';
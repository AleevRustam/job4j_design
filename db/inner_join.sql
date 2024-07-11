create table driver_license(
                         id serial primary key,
                         number int
);

create table people(
                       id serial primary key,
                       name varchar(255),
                       driver_license_id int references driver_license(id) unique
);

insert into driver_license(number) values (123456);
insert into driver_license(number) values (123457);
insert into driver_license(number) values (123458);

insert into people(name, driver_license_id) values('Ivan', 1);
insert into people(name, driver_license_id) values('Boris', 2);
insert into people(name, driver_license_id) values('Petr', 3);


select p.name, d.number
from people as p join driver_license as d on p.driver_license_id = d.id;

select p.name as Имя, d.number as Номер
from people as p join driver_license as d on p.driver_license_id = d.id;

select p.name as "Имя владельца", d.number Номер_водительского_удостоверения
from people p join driver_license d on p.driver_license_id = d.id;
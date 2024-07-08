create table staff (
    id serial primary key, 
    name varchar(256),
	surname text,
	level int
);
insert into staff (name, surname, level) values ('Ivan', 'Ivanov', 1);
select * from staff;
update staff set level = 3;
select * from staff;
delete from staff;
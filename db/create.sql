CREATE TABLE comments(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE attachs(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE items(
	id serial primary key,
	name varchar(255),
	comment_id int REFERENCES comments(id),
	attach_id int REFERENCES attachs(id)
);

CREATE TABLE categories(
	id serial primary key,
	name varchar(255),
	item_id int REFERENCES items(id)
);

CREATE TABLE states(
	id serial primary key,
	name varchar(255),
	item_id int REFERENCES items(id)
);

CREATE TABLE roles(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE users(
	id serial primary key,
	name varchar(255),
	item_id int REFERENCES items(id),
	role_id int REFERENCES roles(id)
);

CREATE TABLE rules(
	id serial primary key,
	name varchar(255),
	role_id int REFERENCES roles(id)
);

ALTER TABLE roles ADD COLUMN rule_id int REFERENCES rules(id);
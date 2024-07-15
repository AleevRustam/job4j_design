CREATE TABLE comments(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE attachs(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE categories(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE states(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE items(
	id serial primary key,
	name varchar(255),
	comment_id int REFERENCES comments(id),
	attach_id int REFERENCES attachs(id),
	category_id int REFERENCES categories(id),
	state_id int REFERENCES states(id)
);


CREATE TABLE rules(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE roles(
	id serial primary key,
	name varchar(255),
	rule_id int REFERENCES rules(id)
);

CREATE TABLE users(
	id serial primary key,
	name varchar(255),
	item_id int REFERENCES items(id),
	role_id int REFERENCES roles(id)
);


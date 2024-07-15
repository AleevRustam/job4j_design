CREATE TABLE roles(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE users(
	id serial primary key,
	name varchar(255),
	role_id INT REFERENCES roles(id)
);

CREATE TABLE rules(
	id serial primary key,
	name varchar(255),
	role_id INT REFERENCES roles(id)
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
	user_id int REFERENCES users(id),
	category_id int REFERENCES users(id),
	state_id int REFERENCES states(id)
);

CREATE TABLE comments(
	id serial primary key,
	name varchar(255),
	item_id INT REFERENCES items(id)
);

CREATE TABLE attachs(
	id serial primary key,
	name varchar(255),
	item_id INT REFERENCES items(id)
);













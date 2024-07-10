CREATE TABLE drivers_license(
	id serial primary key,
	number int
);

CREATE TABLE drivers(
	id serial primary key,
	name varchar(255)
	
);

CREATE TABLE drivers_drivers_license(
	id serial primary key,
	driver_id int REFERENCES drivers_license(id) UNIQUE,
	drivers_license_id int REFERENCES drivers(id) UNIQUE
);

INSERT INTO drivers(name) VALUES ('Rustam');
INSERT INTO drivers(name) VALUES ('Ivan');
INSERT INTO drivers(name) VALUES ('Petr');

INSERT INTO drivers_license(number) VALUES (111);
INSERT INTO drivers_license(number) VALUES (222);
INSERT INTO drivers_license(number) VALUES (333);

INSERT INTO drivers_drivers_license(driver_id, drivers_license_id) VALUES (1, 1);
INSERT INTO drivers_drivers_license(driver_id, drivers_license_id) VALUES (2, 2);
INSERT INTO drivers_drivers_license(driver_id, drivers_license_id) VALUES (3, 3);



SELECT * FROM drivers_drivers_license;

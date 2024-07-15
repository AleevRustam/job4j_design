INSERT INTO roles(name) VALUES ('Role 1');
INSERT INTO roles(name) VALUES ('Role 2');
INSERT INTO roles(name) VALUES ('Role 3');

INSERT INTO users(name, role_id) VALUES ('User 1', 1);
INSERT INTO users(name, role_id) VALUES ('User 2', 1);
INSERT INTO users(name, role_id) VALUES ('User 3', 3);

INSERT INTO rules(name, role_id) VALUES ('Rule 1', 1);
INSERT INTO rules(name, role_id) VALUES ('Rule 2', 2);
INSERT INTO rules(name, role_id) VALUES ('Rule 3', 2);

INSERT INTO categories(name) VALUES ('Category 1');
INSERT INTO categories(name) VALUES ('Category 2');
INSERT INTO categories(name) VALUES ('Category 3');

INSERT INTO states(name) VALUES ('State 1');
INSERT INTO states(name) VALUES ('State 2');
INSERT INTO states(name) VALUES ('State 3');

INSERT INTO items(name, user_id, category_id, state_id) VALUES ('Item 1', 1, 1, 1);
INSERT INTO items(name, user_id, category_id, state_id) VALUES ('Item 2', 1, 2, 2);
INSERT INTO items(name, user_id, category_id, state_id) VALUES ('Item 3', 3, 3, 3);

INSERT INTO comments(name, item_id) VALUES ('Comment 1', 1);
INSERT INTO comments(name, item_id) VALUES ('Comment 2', 2);
INSERT INTO comments(name, item_id VALUES ('Comment 3', 3);

INSERT INTO attachs(name, item_id) VALUES ('Attachment 1', 1);
INSERT INTO attachs(name, item_id) VALUES ('Attachment 2', 1);
INSERT INTO attachs(name, item_id) VALUES ('Attachment 3', 3);










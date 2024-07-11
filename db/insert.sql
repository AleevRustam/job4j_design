INSERT INTO comments(name) VALUES ('Comment 1');
INSERT INTO comments(name) VALUES ('Comment 2');
INSERT INTO comments(name) VALUES ('Comment 3');

INSERT INTO attachs(name) VALUES ('Attachment 1');
INSERT INTO attachs(name) VALUES ('Attachment 2');
INSERT INTO attachs(name) VALUES ('Attachment 3');

INSERT INTO items(name, comment_id, attach_id) VALUES ('Item 1', 1, 1);
INSERT INTO items(name, comment_id, attach_id) VALUES ('Item 2', 2, 2);
INSERT INTO items(name, comment_id, attach_id) VALUES ('Item 3', 3, 3);

INSERT INTO categories(name, item_id) VALUES ('Category 1', 1);
INSERT INTO categories(name, item_id) VALUES ('Category 2', 2);
INSERT INTO categories(name, item_id) VALUES ('Category 3', 3);

INSERT INTO states(name, item_id) VALUES ('State 1', 1);
INSERT INTO states(name, item_id) VALUES ('State 2', 2);
INSERT INTO states(name, item_id) VALUES ('State 3', 3);

INSERT INTO roles(name) VALUES ('Role 1');
INSERT INTO roles(name) VALUES ('Role 2');
INSERT INTO roles(name) VALUES ('Role 3');

INSERT INTO users(name, item_id, role_id) VALUES ('User 1', 1, 1);
INSERT INTO users(name, item_id, role_id) VALUES ('User 2', 2, 2);
INSERT INTO users(name, item_id, role_id) VALUES ('User 3', 3, 3);

INSERT INTO rules(name, role_id) VALUES ('Rule 1', 1);
INSERT INTO rules(name, role_id) VALUES ('Rule 2', 2);
INSERT INTO rules(name, role_id) VALUES ('Rule 3', 3);
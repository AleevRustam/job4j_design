INSERT INTO comments(name) VALUES ('Comment 1');
INSERT INTO comments(name) VALUES ('Comment 2');
INSERT INTO comments(name) VALUES ('Comment 3');

INSERT INTO attachs(name) VALUES ('Attachment 1');
INSERT INTO attachs(name) VALUES ('Attachment 2');
INSERT INTO attachs(name) VALUES ('Attachment 3');

INSERT INTO categories(name) VALUES ('Category 1');
INSERT INTO categories(name) VALUES ('Category 2');
INSERT INTO categories(name) VALUES ('Category 3');

INSERT INTO states(name) VALUES ('State 1');
INSERT INTO states(name) VALUES ('State 2');
INSERT INTO states(name) VALUES ('State 3');

INSERT INTO items(name, comment_id, attach_id, category_id, state_id) VALUES ('Item 1', 1, 1, 1, 1);
INSERT INTO items(name, comment_id, attach_id, category_id, state_id) VALUES ('Item 2', 2, 2, 2, 2);
INSERT INTO items(name, comment_id, attach_id, category_id, state_id) VALUES ('Item 3', 3, 3, 3, 3);

INSERT INTO rules(name) VALUES ('Rule 1');
INSERT INTO rules(name) VALUES ('Rule 2');
INSERT INTO rules(name) VALUES ('Rule 3');

INSERT INTO roles(name, rule_id) VALUES ('Role 1', 1);
INSERT INTO roles(name, rule_id) VALUES ('Role 2', 2);
INSERT INTO roles(name, rule_id) VALUES ('Role 3', 3);

INSERT INTO users(name, item_id, role_id) VALUES ('User 1', 1, 1);
INSERT INTO users(name, item_id, role_id) VALUES ('User 2', 2, 2);
INSERT INTO users(name, item_id, role_id) VALUES ('User 3', 3, 3);

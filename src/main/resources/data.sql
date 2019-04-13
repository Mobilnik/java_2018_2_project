--fixme do not execute each time?
INSERT INTO product_categories(id, name) VALUES (1, 'Food');
INSERT INTO product_categories(id, name) VALUES (2, 'Clothes');
INSERT INTO product_categories(id, name) VALUES (3, 'Books');
INSERT INTO product_categories(id, name) VALUES (4, 'Games');
INSERT INTO product_categories(id, name) VALUES (5, 'Music');
INSERT INTO product_categories(id, name) VALUES (6, 'Films');
INSERT INTO product_categories(id, name) VALUES (7, 'Other');

INSERT INTO products(id, category_id, name, photo, price) VALUES (1, 1, 'Bread', null, 25.7);
INSERT INTO products(id, category_id, name, photo, price) VALUES (2, 1, 'Milk', null, 43.4);
INSERT INTO products(id, category_id, name, photo, price) VALUES (3, 2, 'MIPT T-Shirt', null, 650.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (4, 3, 'Physics by Sivukhin', null, 850.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (5, 3, 'Java by Hortsmann', null, 2000.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (6, 3, 'Java Philosophy by Eckel', null, 3000.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (7, 4, 'Escape from Beck', null, 15000.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (8, 5, 'Ovchinkin singing CD #1', null, 330.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (9, 5, 'Ovchinkin singing CD #2', null, 330.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (10, 6, 'Ovchinkin stand-ups: mechanics', null, 450.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (11, 6, 'Ovchinkin stand-ups: thermodynamics', null, 450.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (12, 6, 'Ovchinkin stand-ups: electricity', null, 450.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (13, 6, 'Ovchinkin stand-ups: optics', null, 450.0);
INSERT INTO products(id, category_id, name, photo, price) VALUES (14, 6, 'Ovchinkin stand-ups: quantum physics', null, 450.0);
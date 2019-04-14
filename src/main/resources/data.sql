--fixme do not execute each time?

DELETE FROM orders_products;
DELETE FROM products;
DELETE FROM product_categories;
DELETE FROM users;
DELETE FROM orders;

INSERT INTO product_categories(id, name) VALUES (1, 'Food');
INSERT INTO product_categories(id, name) VALUES (2, 'Clothes');
INSERT INTO product_categories(id, name) VALUES (3, 'Books');
INSERT INTO product_categories(id, name) VALUES (4, 'Games');
INSERT INTO product_categories(id, name) VALUES (5, 'Music');
INSERT INTO product_categories(id, name) VALUES (6, 'Films');
INSERT INTO product_categories(id, name) VALUES (7, 'Other');

--Разрешены только относительные пути для файлов при такой загрузке
--Директория, относительно которой нужно прописать путь: SHOW data_directory;
--У меня это /usr/local/var/postgres
INSERT INTO products(id, category_id, name, photo, price)
VALUES (1, 1, 'Bread', pg_read_binary_file('data/images/bread.jpg'), 25.7);
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

INSERT INTO users(id, email, name, photo, password_hash, password_salt)
VALUES (1, 'iaulitin@yandex.ru', 'iaulitin', null, null, null);

INSERT INTO orders(id, user_id, status_code, updated_date_time, comment)
VALUES (1, 1, 0, '2019-04-13 16:50:03.268000', 'It would be nice to receive my order ASAP.');
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (1, 1, 10);
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (1, 2, 20);
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (1, 7, 15);

INSERT INTO orders(id, user_id, status_code, updated_date_time, comment)
VALUES (2, 1, 1, '2019-03-14 10:00:46.668000', 'Hope you have nice service!!!1');
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (2, 4, 9);
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (2, 3, 7);
INSERT INTO orders_products(order_id, product_id, quantity) VALUES (2, 6, 9)
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY,
  email VARCHAR(100) UNIQUE NOT NULL,
  name VARCHAR(100) UNIQUE NOT NULL,
  photo BYTEA,
  password_hash VARCHAR(255),
  password_salt VARCHAR(32)
);
COMMENT ON TABLE users IS 'Table containing the application users'' data';
COMMENT ON COLUMN users.id IS 'User''s identifier';
COMMENT ON COLUMN users.email IS 'User''s email';
COMMENT ON COLUMN users.name IS 'User''s name';
COMMENT ON COLUMN users.photo IS 'Byte array with user''s photo';
COMMENT ON COLUMN users.password_hash IS 'User''s password hash';
COMMENT ON COLUMN users.password_salt IS 'A salt to calculate a password hash';

CREATE SEQUENCE IF NOT EXISTS user_id_sequence START WITH 1 MINVALUE 1 INCREMENT BY 1;
COMMENT ON SEQUENCE user_id_sequence IS 'Sequence for identifiers of table ''users''';



CREATE TABLE IF NOT EXISTS product_categories (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100)
);
COMMENT ON TABLE product_categories IS 'Table containing the application product categories'' data';
COMMENT ON COLUMN product_categories.id IS 'Categories''s identifier';
COMMENT ON COLUMN product_categories.name IS 'Categories'' name';



CREATE TABLE IF NOT EXISTS products (
  id BIGINT PRIMARY KEY,
  category_id BIGINT REFERENCES product_categories(id) ON DELETE CASCADE,
  name VARCHAR(100) NOT NULL,
  photo BYTEA,
  price NUMERIC
);
COMMENT ON TABLE products IS 'Table containing the application products'' data';
COMMENT ON COLUMN products.id IS 'Product''s identifier';
COMMENT ON COLUMN products.category_id IS 'Product''s categories'' identifier';
COMMENT ON COLUMN products.name IS 'Product''s name';
COMMENT ON COLUMN products.photo IS 'Byte array with product''s photo';
COMMENT ON COLUMN products.price IS 'Product''s price';

CREATE SEQUENCE IF NOT EXISTS  product_id_sequence START WITH 1 MINVALUE 1 INCREMENT BY 1;
COMMENT ON SEQUENCE product_id_sequence IS 'Sequence for identifiers of table ''products''';



CREATE TABLE IF NOT EXISTS orders (
  id BIGINT PRIMARY KEY,
  user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
  status_code SMALLINT,
  updated_date_time TIMESTAMP,
  comment VARCHAR(500)
);
COMMENT ON TABLE orders IS 'Table containing the application orders'' data';
COMMENT ON COLUMN orders.id IS 'Order''s identifier';
COMMENT ON COLUMN orders.user_id IS 'Id of the user that made an order';
COMMENT ON COLUMN orders.status_code IS 'Code of order''s status';
COMMENT ON COLUMN orders.updated_date_time IS 'Last change date and time';
COMMENT ON COLUMN orders.comment IS 'Comment on order provided by a customer';


CREATE SEQUENCE IF NOT EXISTS order_id_sequence START WITH 1 MINVALUE 1 INCREMENT BY 1;
COMMENT ON SEQUENCE order_id_sequence IS 'Sequence for identifiers of table ''orders''';



CREATE TABLE IF NOT EXISTS orders_products (
  order_id BIGINT REFERENCES orders (id) ON DELETE CASCADE,
  product_id BIGINT REFERENCES products (id) ON DELETE CASCADE,
  quantity INTEGER NOT NULL,
  CONSTRAINT orders_products_pk PRIMARY KEY (order_id, product_id)
);

COMMENT ON TABLE orders_products IS 'Linkage table between tables ''orders'' and ''products'' ';
COMMENT ON COLUMN orders_products.order_id IS 'Order''s identifier';
COMMENT ON COLUMN orders_products.product_id IS 'Product''s identifier';
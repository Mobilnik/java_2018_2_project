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



CREATE TABLE IF NOT EXISTS goods (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  photo BYTEA,
  price NUMERIC
);

COMMENT ON TABLE goods IS 'Table containing the application goods'' data';
COMMENT ON COLUMN goods.id IS 'Good''s identifier';
COMMENT ON COLUMN goods.name IS 'Good''s name';
COMMENT ON COLUMN goods.photo IS 'Byte array with good''s photo';
COMMENT ON COLUMN goods.price IS 'Good''s price';

CREATE SEQUENCE IF NOT EXISTS  good_id_sequence START WITH 1 MINVALUE 1 INCREMENT BY 1;
COMMENT ON SEQUENCE good_id_sequence IS 'Sequence for identifiers of table ''goods''';



CREATE TABLE IF NOT EXISTS orders (
  id BIGINT PRIMARY KEY,
  --FOREIGN KEY
  user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
  status_code SMALLINT
);

COMMENT ON TABLE orders IS 'Table containing the application orders'' data';
COMMENT ON COLUMN orders.id IS 'Order''s identifier';
COMMENT ON COLUMN orders.user_id IS 'Id of the user that made an order';
COMMENT ON COLUMN orders.status_code IS 'Code of order''s status';

CREATE SEQUENCE IF NOT EXISTS order_id_sequence START WITH 1 MINVALUE 1 INCREMENT BY 1;
COMMENT ON SEQUENCE order_id_sequence IS 'Sequence for identifiers of table ''orders''';



CREATE TABLE IF NOT EXISTS orders_goods (
  order_id BIGINT  REFERENCES orders (id),
  good_id BIGINT REFERENCES orders (id),
  CONSTRAINT orders_goods_pk PRIMARY KEY (order_id, good_id)
);

COMMENT ON TABLE orders_goods IS 'Linkage table between tables ''orders'' and ''goods'' ';
COMMENT ON COLUMN orders_goods.order_id IS 'Order''s identifier';
COMMENT ON COLUMN orders_goods.good_id IS 'Good''s identifier';
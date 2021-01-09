--
-- H2 database dump
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS shopping_cart_item;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;


-- Таблица address
CREATE TABLE address
(
  id  bigserial NOT NULL,
  index character varying(60),
  region character varying(60),
  city character varying(60),
  street character varying(60),
  home_number character varying(60),
  flat integer NOT NULL,
  recipient character varying(60) NOT NULL,
  CONSTRAINT address_pkey PRIMARY KEY (id)
);


-- Таблица аккаунтов
CREATE TABLE user
(
  id  bigserial NOT NULL,
  name character varying(60),
  email character varying(60) NOT NULL,
  password character varying(80) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT account_email_key UNIQUE (email)
);

-- Таблица прав доступа
CREATE TABLE role
(
  id  bigserial NOT NULL,
  role character varying NOT NULL,
  CONSTRAINT role_pk PRIMARY KEY (id)
);


-- Таблица прав доступа и соотвутствующих им акк
CREATE TABLE user_role
(
  id_user integer NOT NULL,
  id_role integer NOT NULL,
  CONSTRAINT user_role_pk PRIMARY KEY (id_user, id_role),
  CONSTRAINT user_role_user_id_fk FOREIGN KEY (id_user) REFERENCES user (id),
  CONSTRAINT user_role_role_id_fk FOREIGN KEY (id_role) REFERENCES role (id)
);

-- Таблица продуктов магазина
CREATE TABLE product
(
  id bigserial NOT NULL,
  name character varying(255) NOT NULL,
  length numeric(8,2) NOT NULL,
  width numeric(8,2) NOT NULL,
  height numeric(8,2) NOT NULL,
  price numeric(8,2) NOT NULL,
  status character varying(255) NOT NULL,
  image_url character varying(255) NOT NULL,
  totalCount integer NOT NULL, 
  CONSTRAINT product_pkey PRIMARY KEY (id)
);


-- Таблица корзины
CREATE TABLE shopping_cart
(
  id bigserial NOT NULL,
  id_user integer NOT NULL,
  total_count integer NOT NULL,
  total_cost numeric(8,2) NOT NULL, 
  CONSTRAINT shopping_cart_pk PRIMARY KEY (id),
  CONSTRAINT shopping_cart_user_id_fk FOREIGN KEY (id_user)
      REFERENCES user (id)
     
);

-- Таблица товаров в корзине
CREATE TABLE shopping_cart_item
 (
  id bigserial NOT NULL,
  id_shopping_cart bigint NOT NULL,
  id_product integer NOT NULL,
  count integer,
  CONSTRAINT shopping_cart_item_pk PRIMARY KEY (id),
  CONSTRAINT shopping_cart_item_id_fk FOREIGN KEY (id_shopping_cart)
      REFERENCES shopping_cart (id),
  CONSTRAINT shopping_cart_item_product_id_fk FOREIGN KEY (id_product)
      REFERENCES product (id)
);


-- Таблица заказов
CREATE TABLE "order"
(
  id bigserial NOT NULL,
  id_user integer NOT NULL,
  created timestamp without time zone NOT NULL,
  execution_status character varying(60),
  id_address integer NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY (id),
  CONSTRAINT order_user_id_fk FOREIGN KEY (id_user)
      REFERENCES user (id),
  CONSTRAINT order_address_id_fk FOREIGN KEY (id_address)
      REFERENCES address (id)    
);

-- Таблица товаров в заказе
CREATE TABLE order_item
 (
  id bigserial NOT NULL,
  id_order bigint NOT NULL,
  id_product integer NOT NULL,
  count integer,
  CONSTRAINT order_item_pk PRIMARY KEY (id),
  CONSTRAINT order_item_order_id_fk FOREIGN KEY (id_order)
      REFERENCES "order" (id),
  CONSTRAINT order_item_product_id_fk FOREIGN KEY (id_product)
      REFERENCES product (id)
);

-- Добавим роли
INSERT INTO role (id, role) VALUES (DEFAULT, 'ROLE_USER');
INSERT INTO role (id, role) VALUES (DEFAULT, 'ROLE_ADMIN');

-- Добавим продукты в магазин

 INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
  VALUES (null, 'Songesand', 119.60, 60.00, 191.00, 189.00, 'in stock', '/static/img/0555114_PE660180_S4_SONGESAND.jpg', 12);
 INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
  VALUES (null, 'Bryggja', 120.00, 43.00, 173.00, 159.00, 'in stock', '/static/img/0610068_PE689978_S4_BRYGGJA.jpg', 2);
 INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
 VALUES (null, 'Hemnes', 120.00, 59.00, 197.00, 239.00, 'in stock', '/static/img/0625541_PE692340_S4_HEMNES.jpg', 22);
  INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
 VALUES (null, 'Tyssedal', 88.00, 58.00, 208.00, 299.00, 'under order', '/static/img/0626566_PE692870_S4_Tyssedal.jpg', 0);
  INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
 VALUES (null, 'TRYSIL', 79.40, 61.20, 201.70, 169.00, 'out of stock', '/static/img/0626571_PE692875_S4_TRYSIL.jpg', 0);
  INSERT INTO PUBLIC.PRODUCT (ID, NAME, LENGTH, WIDTH, HEIGHT, PRICE, STATUS, IMAGE_URL, TOTALCOUNT)
 VALUES (null, 'Sunlandet', 119.60, 60.00, 191.00, 189.00, 'in stock', '/static/img/0668247_PE716770_S5_SUNLANDET.jpg', 12);
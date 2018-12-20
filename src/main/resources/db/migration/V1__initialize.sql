SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(15) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id,role_id),

--  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
  REFERENCES roles (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  id	              INT(11) NOT NULL AUTO_INCREMENT,
  title               VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id	              INT(11) NOT NULL AUTO_INCREMENT,
  category_id         INT(11) NOT NULL,
  vendor_code         VARCHAR(8) NOT NULL,
  image               VARCHAR(255),
  title               VARCHAR(255) NOT NULL,
  short_description   VARCHAR(1000) NOT NULL,
  full_description    VARCHAR(5000) NOT NULL,
  price               DECIMAL(8,2) NOT NULL,
  create_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id)
  REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders_statuses;

CREATE TABLE orders_statuses (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  id	      INT(11) NOT NULL AUTO_INCREMENT,
  user_id     INT(11) NOT NULL,
  price       DECIMAL(8,2) NOT NULL,
  status_id   INT(11) NOT NULL,
  create_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  order_code  VARCHAR(255) NULL,
  UNIQUE INDEX order_code_UNIQUE (order_code ASC),
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
  REFERENCES users (id),
  CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)
  REFERENCES orders_statuses (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders_item;

CREATE TABLE orders_item (
  id	      INT(11) NOT NULL AUTO_INCREMENT,
  product_id  INT(11) NOT NULL,
  order_id    INT(11) NOT NULL,
  quantity    INT NOT NULL,
  total_price DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
  REFERENCES orders (id),
  CONSTRAINT FK_PRODUCT_ID FOREIGN KEY (product_id)
  REFERENCES products (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO roles (name)
VALUES
('ROLE_EMPLOYEE'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (username,password,first_name,last_name,email,phone)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Юрий','Admin','uatilman@gmail.com','+79104668422');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

INSERT INTO categories (title)
VALUES
("Телевизоры"), ("Ноутбуки");

INSERT INTO orders_statuses (title)
VALUES
("Сформирован");

INSERT INTO products (category_id, vendor_code, image, title, short_description, full_description, price)
VALUES
(1, "00000001", "null", "40\" Телевизор Samsung UE40NU7170U", "Коротко: Хороший телевизор Samsung 40", "LED телевизор Samsung 40", 26000.00),
(1, "00000002", "null", "48\" Телевизор Samsung UE48NU7170U", "Коротко: Хороший телевизор Samsung 48", "LED телевизор Samsung 48", 32000.00),
(1, "00000003", "null", "56\" Телевизор Samsung UE56NU7170U", "Коротко: Хороший телевизор Samsung 56", "LED телевизор Samsung 56", 44000.00);
CREATE DATABASE IF NOT EXISTS 'restaurant';
USE 'restaurant';

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    accountId VARCHAR(40) NOT NULL,
    password VARCHAR(60) NOT NULL,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT NULL,
    update_time DATETIME DEFAULT NULL,
    create_employee INT DEFAULT NULL,
    update_employee INT DEFAULT NULL,
    PRIMARY KEY(id)
);
INSERT INTO employee VALUES (1,'root','root','123456',1,'2025-02-17','2025-02-27',1,1);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    status INT DEFAULT NULL,
    create_time DATETIME DEFAULT NULL,
    update_time DATETIME DEFAULT NULL,
    create_employee INT DEFAULT NULL,
    update_employee INT DEFAULT NULL,
    PRIMARY KEY(id)
);
INSERT INTO category VALUES (1,'Main',1,'2025-02-17','2025-02-27',1,1);

DROP TABLE IF EXISTS item;
CREATE TABLE item (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    price decimal(10,2) NOT NULL,
    image VARCHAR(255) DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT NULL,
    update_time DATETIME DEFAULT NULL,
    create_employee INT DEFAULT NULL,
    update_employee INT DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
INSERT INTO item VALUES (1,'French Fries',1,5.6,'','',1,'2025-02-17','2025-02-27',1,1);
INSERT INTO item VALUES (2,'Beef Burger',1,8.9,'','',1,'2025-02-17','2025-02-27',1,1);
INSERT INTO item VALUES (3,'Pepperoni Pizza',1,15.99,'','',1,'2025-02-17','2025-02-27',1,1);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    table_number INT NOT NULL,
    status INT DEFAULT 1,
    total_amount decimal(10,2) NOT NULL,
    payment_status INT DEFAULT 1,
    payment_method INT DEFAULT 1,
    create_time DATETIME DEFAULT NULL,
    update_time DATETIME DEFAULT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS order_details;
CREATE TABLE order_details (
    id INT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    price decimal(10,2) NOT NULL,
    remark VARCHAR(255) DEFAULT NULL,
    status INT DEFAULT 1,
    create_time DATETIME DEFAULT NULL,
    update_time DATETIME DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
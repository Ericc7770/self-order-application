CREATE DATABASE IF NOT EXISTS 'restaurant';
USE 'restaurant';

DROP TABLE IF EXISTS 'employee';
CREATE TABLE 'employee' (
    'id' int NOT NULL AUTO_INCREMENT,
    'name' varchar(100) NOT NULL,
    'accountId' varchar(40) NOT NULL,
    'password' varchar(60) NOT NULL,
    'status' int DEFAULT 1,
    'create_time' datetime DEFAULT NULL,
    'update_time' datetime DEFAULT NULL,
    'create_employee' int DEFAULT NULL,
    'update_employee' int DEFAULT NULL,
    PRIMARY KEY('id')
);
INSERT INTO employee VALUES (1,'root','root','123456',1,'2025-02-17','2025-02-27',1,1);

DROP TABLE IF EXISTS 'category';
CREATE TABLE 'category' (
    'id' int NOT NULL AUTO_INCREMENT,
    'name' varchar(50) NOT NULL,
    'status' int DEFAULT NULL,
    'create_time' datetime DEFAULT NULL,
    'update_time' datetime DEFAULT NULL,
    'create_employee' int DEFAULT NULL,
    'update_employee' int DEFAULT NULL,
    PRIMARY KEY('id')
);
INSERT INTO category VALUES (1,'Main',1,'2025-02-17','2025-02-27',1,1);

DROP TABLE IF EXISTS 'item';
CREATE TABLE 'item' (
    'id' int NOT NULL AUTO_INCREMENT,
    'name' varchar(50) NOT NULL,
    'category_id' int NOT NULL,
    'price' decimal(10,2) NOT NULL,
    'image' varchar(255) DEFAULT NULL,
    'description' varchar(255) DEFAULT NULL,
    'status' int DEFAULT 1,
    'create_time' datetime DEFAULT NULL,
    'update_time' datetime DEFAULT NULL,
    'create_employee' int DEFAULT NULL,
    'update_employee' int DEFAULT NULL,
    PRIMARY KEY('id'),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
INSERT INTO item VALUES (1,'French Fries',1,5.6,'','',1,'2025-02-17','2025-02-27',1,1);
INSERT INTO item VALUES (2,'Beef Burger',1,8.9,'','',1,'2025-02-17','2025-02-27',1,1);
INSERT INTO item VALUES (3,'Pepperoni Pizza',1,15.99,'','',1,'2025-02-17','2025-02-27',1,1);
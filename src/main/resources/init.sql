CREATE DATABASE homework_28;

USE homework_28;

CREATE TABLE orders (
orderNumber INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
customerName VARCHAR(255) NOT NULL,
orderDescription VARCHAR(255) NOT NULL
);

INSERT INTO orders(orderNumber,customerName,orderDescription) VALUES
(1,"Mike","coffee"),
(2,"Jim","coffee"),
(3,"George","coffee"),
(4,"Kate","coffee"),
(5,"Lisa","coffee"),
(6,"Don","coffee"),
(7,"Jimmy","coffee"),
(8,"George","coffee"),
(9,"Michael","coffee"),
(10,"Sandra","coffee")
;


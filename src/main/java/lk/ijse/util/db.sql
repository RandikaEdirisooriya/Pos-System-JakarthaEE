CREATE DATABASE IF NOT EXISTS inventorypos;

USE inventorypos;

-- Table for Customer
CREATE TABLE Customer (
                          id VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255),
                          salary DOUBLE
);

-- Table for Item
CREATE TABLE Item (
                      id VARCHAR(255) PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      price DOUBLE,
                      qty INT
);

-- Table for Order
CREATE TABLE `Order` (
                         orderId VARCHAR(255) PRIMARY KEY,
                         orderDate DATE,
                         cusIdOption VARCHAR(255),
                         itemIdOption VARCHAR(255),
                         orderQty INT,
                         total DOUBLE,
                         txtCash DOUBLE,
                         txtDiscount DOUBLE,
                         FOREIGN KEY (cusIdOption) REFERENCES Customer(id),
                         FOREIGN KEY (itemIdOption) REFERENCES Item(id)
);

-- Table for OrderDetail
CREATE TABLE OrderDetail (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id VARCHAR(255),
                             item_id VARCHAR(255),
                             orderQty INT,
                             itemPrice DOUBLE,
                             FOREIGN KEY (order_id) REFERENCES `Order`(orderId) ON DELETE CASCADE,
                             FOREIGN KEY (item_id) REFERENCES Item(id) ON DELETE CASCADE
);

-- Table for User
CREATE TABLE User (
                      id VARCHAR(255) PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL
);

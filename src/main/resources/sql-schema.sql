drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NULL DEFAULT NULL,
    `item_price` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);


CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NOT NULL,
    PRIMARY KEY(`id`),
	CONSTRAINT `customer_id` FOREIGN KEY(`customer_id`) REFERENCES `ims`.`customers`(`id`)
    );   

CREATE TABLE IF NOT EXISTS `ims`.`order_item` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_id` INT(11) NOT NULL,
    `order_id` INT(11) NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `items_id` FOREIGN KEY(`item_id`) REFERENCES `ims`.`items`(`item_id`),
    CONSTRAINT `order_id` FOREIGN KEY(`order_id`) REFERENCES `ims`.`orders`(`id`)
    );

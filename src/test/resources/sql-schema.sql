drop schema testims;
CREATE SCHEMA IF NOT EXISTS `testims`;
USE `testims`;
CREATE TABLE IF NOT EXISTS `testims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `testims`.`items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NULL DEFAULT NULL,
    `item_price` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);


CREATE TABLE IF NOT EXISTS `testims`.`orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NOT NULL,
    PRIMARY KEY(`id`),
	CONSTRAINT `FK_customer_id` FOREIGN KEY(`customer_id`) REFERENCES `testims`.`customers`(`id`)
    );   

CREATE TABLE IF NOT EXISTS `testims`.`order_item` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_id` INT(11) NOT NULL,
    `order_id` INT(11) NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `FK_items_id` FOREIGN KEY(`item_id`) REFERENCES `testims`.`items`(`item_id`),
    CONSTRAINT `FK_order_id` FOREIGN KEY(`order_id`) REFERENCES `testims`.`orders`(`id`)
);
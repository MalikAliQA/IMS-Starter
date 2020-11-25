INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Malik', 'Ali');

INSERT INTO `ims`.`items` (`item_name`, `item_price`) VALUES ('Overwatch', '19.99');
INSERT INTO `ims`.`items` (`item_name`, `item_price`) VALUES ('Call Of Duty: B03', '44.99');

INSERT INTO `ims`.`orders`(`customer_id`) VALUES (1);
INSERT INTO `ims`.`orders`(`customer_id`) VALUES (2);

INSERT INTO `ims`.`order_item`(`item_id`, `order_id`) VALUES (1, 1);
INSERT INTO `ims`.`order_item`(`item_id`, `order_id`) VALUES (2, 1);
INSERT INTO `ims`.`order_item`(`item_id`, `order_id`) VALUES (2, 2);
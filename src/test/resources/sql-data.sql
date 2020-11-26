INSERT INTO `testims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `testims`.`customers` (`first_name`, `surname`) VALUES ('Malik', 'Ali');

INSERT INTO `testims`.`items` (`item_name`, `item_price`) VALUES ('Overwatch', '19.99');
INSERT INTO `testims`.`items` (`item_name`, `item_price`) VALUES ('Call Of Duty: B03', '44.99');

INSERT INTO `testims`.`orders`(`customer_id`) VALUES (1);
INSERT INTO `testims`.`orders`(`customer_id`) VALUES (2);


INSERT INTO `testims`.`order_item`(`order_id`, `item_id`) VALUES (1, 2);
INSERT INTO `testims`.`order_item`(`order_id`, `item_id`) VALUES (1, 1);
INSERT INTO `testims`.`order_item`(`order_id`, `item_id`) VALUES (2, 2);


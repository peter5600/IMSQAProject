/*   drop schema ims; remove this so the data remains */
/* whilst testing have this enabled*/

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`) 
);

CREATE TABLE IF NOT EXISTS `ims`.`items`(
	`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(150) NOT NULL,
    `cost` float NOT NULL
);

CREATE TABLE IF NOT EXISTS `ims`.`TBOrder`(
	`id` INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `CustomerID` INT(11) NOT NULL,
    foreign key(CustomerID) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`orderlines`(
	`id` INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `OrdersID` INT(11) NOT NULL,
    `ItemID` INT(11) NOT NULL,
    `Quantity` INT(11) NOT NULL,
    foreign key(OrdersID) REFERENCES TBOrder(id) ON DELETE CASCADE,
    foreign key(ItemID) REFERENCES items(id) ON DELETE CASCADE
);

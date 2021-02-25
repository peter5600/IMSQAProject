SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `orderlines`;
DROP TABLE IF EXISTS `TBOrder`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `customers`;




CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`) 
);

CREATE TABLE IF NOT EXISTS `items`(
	`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(150) NOT NULL,
    `cost` float NOT NULL,
    `UserID` INT(11)/*,
    foreign key(UserID) REFERENCES users(ID) remove until users added*/
);

CREATE TABLE IF NOT EXISTS `TBOrder`(/*Might wanna change all int(11) to Longh instead*/
	`id` LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `CustomerID` INT(11) NOT NULL,
    `UserID` INT(11) NOT NULL,
    foreign key(CustomerID) REFERENCES customers(id) ON DELETE CASCADE /*,
     foreign key(UserID) REFERENCES users(ID) remove until users added*/
);

CREATE TABLE IF NOT EXISTS `orderlines`(
	`id` INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `OrdersID` INT(11) NOT NULL,
    `ItemID` INT(11) NOT NULL,
    `Quantity` INT(11) NOT NULL,
    foreign key(OrdersID) REFERENCES TBOrder(id) ON DELETE CASCADE,
    foreign key(ItemID) REFERENCES items(id) ON DELETE CASCADE
);



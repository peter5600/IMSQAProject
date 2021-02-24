DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
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
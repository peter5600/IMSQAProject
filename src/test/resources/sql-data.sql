INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`,`cost`,`UserID`) VALUES('Pepsi', 3.50, 1);
INSERT INTO `TBOrder` (`CustomerID`, `UserID`) VALUES(1,1);
INSERT INTO `orderlines` (`OrdersID`, `ItemID`, `Quantity`) VALUES (1,1,5);

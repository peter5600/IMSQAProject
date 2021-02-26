Actual Coverage 76.9% but 3 tests were @Ignored which push the coverage to around 81%

Inventory Managment System



### Prerequisites

You will need an IDE that can handle JAVA code i recommend Eclipse,
If you wish to modify or interact with the database outside of what the application already does you will need an instillation of MySQL
You will need maven to package the applicatyion i recommend 3.6.3 since thats the version this application was built on. 
You will need Java 1.8 or higher up until Java 14.
A local instillation of git

## Getting Started and Installing

First clone the repository onto your system.
If you just want the application you will find a .jar file in the target folder you will need to use the version with dependencies.
If you want to make changes to the application first open the project folder in eclipse or your ide of choice.
After you have made any changes in the application run the application using Eclipse.
When you are ready and want to package the application into a .jar file head to the root folder and using cmd run mvn clean package
this will create two .jar files in you targer folder one with dependencies and one without.

## Using the application
The application has 3 main entities thes are customer, item and order i will explain each of these entities and there commands.

To enter customer on the lauch screen enter customer this will load the customer entity.
Then to create a customer enter create this will ask for a name and a surname.
Then to read all of customers enter read this will read all of the customers in the database and display them to you,
Then to update a customer enter update this will ask for a customer id and a new name and surname,
Then to delete a customer enter delete and this will ask for the customer id then delete the customer.
Then to return to the launch screen enter return this will load the lauch screen.

To enter item on the launch screen enter item and this will load the item entity.
To create an item enter create and then enter an item name and price this will create the item.
To read all the items enter read this will display all of the items to you.
To update an item enter the items id and then a new name and price this will update the item.
To delete an item enter the items id and then this will delete the item from the system.
Then to return to the launch screen enter return this will load the lauch screen.

To enter the order entity on the launch screen enter order and this will load the entity.
To create an order enter order and then enter a customer id and an item id and item quantity you will be asked for an item id and item quantity until you enter finish this will complete the order.
To read an order enter read this will read back all of the orders and the items associated with that order.
To delete an order enter delete this will ask for an id which will delete the order.
To calculate the cost of an order enter cost this will ask for an order id and return the cost of that item.
To delete an item from an order enter deleteitem this will ask for an order id and item id this item will be removed from the order.
To add an item to an order enter additem this will ask for an order id and the item id and the quantity and add it to the order.
Then to return to the launch screen enter return this will load the lauch screen.

To exit the application on the lauch screen enter stop this will close the application.


## Running the tests

To run the tests within the application you can use maven or JUnit both return the same result.
If you are using eclipse then you can right click the project and then run as JUnit test this will return the results which is broken down into pass, fail and errors.
If you want to use maven test then you need to load a terminal in the root of the folder and enter mvn test this will return the results broken down into pass, fail and errors.

### Unit Tests 

My unit tests cover the controllers, DAOs and the objects for each system.
They test the behaviour of the controllers and make sure that they work as expected.
They test the functionality of the DAOs and make sure they return the expected value based on what is entered.
They make sure that the objects return the correct type.

The test below is made using JUnit and mockito it tests the delete function in the order controller.
It passes the ID 1 when asked for an id and returns 1 for the number of records deleted using the delete function in the DAO
and then checks that the delete function does what i have defined aboved using assertEquals to make sure everything works as expected.
```
        long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);
		Mockito.when(OrderLineDAO.DeleteOrderLinesUsingOrderID(ID)).thenReturn(1);

		assertEquals(ID, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
		Mockito.verify(OrderLineDAO, Mockito.times(1)).DeleteOrderLinesUsingOrderID(ID);
```

## Deploying on a Live server
Follow the steps in the Getting started and installing steps.
To deploy to a live server remove ``` drop schema `ims` ``` from the schema file in the main/resources folder this will make sure that the data isn't removed every time that application is launched.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Peter Colquhoun** - *Completed the application*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

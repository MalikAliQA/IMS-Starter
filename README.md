Coverage: 71%
# IMS Starter Project

On inventory management system that allows user interaction via a command line interface CLI.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
 
1. Make sure you have Maven installed 
2. Make sure you have a Java Development Environment installed 
3. Make sure you have Mysql workbench installed 
4. Open the file using your java development environment

### Installing

1. Fork
2. clone respository you created onto your local machine using
3. Create your required branches for development
4. Open Eclipse
5. Open the project as a Maven project
6. Run project as a Java application from Runner class to use it
7. REMEMBER TO CHANGE THE IP ADDRESS TO MATCH YOUR GCP INSTANCE in the DB.utils class
```
this.DB_URL = "jdbc:mysql://ipaddressofinstance/ims?db_name&serverTimezone=UTC";
```


Demo:

You will first need to create a database called ims in your GCP instance using:
```
CREATE DATABASE ims;
```
First it will ask for a user name and password both of with are root
Next it will display:

```
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

If you type in customer it will bring you to the customer menu, all main menus for each entity are the same:

```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```
create will let you enter a first name and surname to create a new customer in the database
```
create
Please enter a first name
alice
Please enter a surname
a
Customer created
```
once done the customer entity menu will be displayed again:

```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```


this time try read to read all the customers:

```
--------------------------------------------------------------------------------
Customer ID: 1 | First Name: jordan | Surname: harrison |
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
Customer ID: 2 | First Name: Malik | Surname: Ali |
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
Customer ID: 3 | First Name: alice | Surname: a |
--------------------------------------------------------------------------------
```

once again the entity customer menu will be displayed:

```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```

return will take you to the main menu:

```
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

typing stop will exit the application:

```
SO LONG!
```


## Running the tests

To run the tests righ click on src/test/java and click run as JUnit test, to get the coverage click coverage as JUnit test

### Unit Tests 

Unit tests are the tests on the DAO classes, these test whether the correct code is ran in a method when data is entered 
e.g. To test the add functionality of the CustomerDAO

```
@Test
	public void testCreate() {
		final Customer created = new Customer(3L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}
```

give the data ID:3 firstname:chris surname: perrins to the method create and creates a customer based off that data then the asserEquals says is the dummy data you supplied the same as creating a customer when the DAO.create method is ran with the dummy data 

### Integration Tests 
These were the mockito tests done on the controller classes creating new data to see if the class is running its method properly testing the user input in a sense 
e.g. for the CustomerController testing the create method 

```
@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
```
The first Mockito.when tells JUnit to see if when it runs utils.getString() the strings that we defined are returned the second when is the same but for the dao.create telling JUnit to run the create class with the data we have given and return if the output is correct 

Mockito.verify is to check how many times each mock is running 

## Deployment

To deploy you have to create a Jar file using maven 

First git bash here in files explorer on the loction of the project 
then once git bash opens type the command:
```
mvn clean package
```
clean just incase there is already a target folder and package to create a jar file
once completed this will create a new folder called target and within that folder there will be two executable files the one ending in jar-with-dependecies is the one that will run on a CLI as that contains all the dependecies the project requires.



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/) - Java IDE
* [GCP MySQL Instance](https://cloud.google.com/) - MySQL instance on the Google Cloud Platform



## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Malik Ali** - *Finalisation*  - [andrewklein](https://github.com/MalikAliQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Piers Barber for answering all my questions no matter how simple or complex they were
* Pawel Stypulkowski for the same
* All previous QA instructors that have helped me brush up on my Java Knowledge

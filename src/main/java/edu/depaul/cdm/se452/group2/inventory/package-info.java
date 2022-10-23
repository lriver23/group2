package edu.depaul.cdm.se452.group2.inventory;

/*
 * @author Louis Rivera
 * 
 * ------- Milestone 1 -------
 * 
 * 9/11/2022
 * Package info and readme have been created.
 * 
 * 
 * ------- Milestone 2 -------
 * 
 * 
 * 9/18/2022
 * Updated the project dependencies to include logging, database, and the webserver.
 * 
 * Created and populated the resouce files, application-prod, application-dev. 
 * Added in the test logging statments when the app is launched to test configuration files
 * 
 * 
 * 9/19/2022
 * Created the java classes that will generate the inventory SQL tables: Product, Seller.
 * 
 * Setup the database configuration so that the database is launched when the spring application is started.
 * 
 * ------- Milestone 3 -------
 * Create service calls so that inventory related items can be manipulated externally.
 * Setup relationships betweeen inventory items. There were issues with some of the foriegn key objects were setup
 * that was causing infinite creation of objects. The structure of the how the inventory items relate to
 * one another had to be changed and the following tag had to be added to some of the classes:
 * @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "Id")
 * 
 * 
 * 
 * ------- Nices to Have -------
 * 
 * - Add in validation for each of the entity objects' fields
 * - Change API calls so they return JSON responses instead of strings
 * - Add tests for each of the API routes
 * - Add tests for the new relationships between the objects 
 * - Seperate out the database logic from the API logic so that they're in different classes
 * - 
 * 
 */
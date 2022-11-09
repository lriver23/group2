package edu.depaul.cdm.se452.group2.inventory;

/*
 * @author Louis Rivera
 * 
 * ******* Additional Features *******
 * Used additional validation constraints like NotNull and Positive in Product.java.
 * 
 * Create service response classes like ProductServiceResponse.java that services can use to construct JSON responses.
 * This helps the client render the response correctly since non-JSON strings are not easilyhandled by the client.
 * 
 * NoSeller.java is using @DocumentReference to establish a one-to-many relationship with products
 * 
 * Used junit's @BeforeEach and @BeforeAll in NoProductServiceTest.java, NoSellerServiceTest.java, and NoStockServiceTest.java to perform common setup between unit tests.
 * 
 * Used lombok's @Builder annotation in NoProduct.java, NoSeller.java, and NoStock.java so that objects could be built easier manaully for testing.
 * The tests that then use these builders are in NoProductServiceTest.java, NoSellerServiceTest.java, and NoStockServiceTest.java.
 * 
 * Security had been enabled for the application so that a username and password was required. This was making it so that testing the
 * serivce was failing because requests were returning with 401 errors. To get around this issue, a security test dependency was
 * added to the build.gradle file. Then in NoProductServiceTest.java, NoSellerServiceTest.java, and NoStockServiceTest.java, 
 * a mock login was created of type RequestPostProcessor and then used when making requests.
 * 
 * Cross Site Request Forgery (CSRF) was causing posts requests to fail in testing. The request had to be maid by passing in
 * a mock CSRF token with the request. You can see it added to the post test in NoProductServiceTest.java, NoSellerServiceTest.java, and NoStockServiceTest.java.
 * 
 * ******* Progress *******
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
 * 
 * Create service calls so that inventory related items can be manipulated externally.
 * Setup relationships betweeen inventory items. There were issues with some of the foriegn key objects were setup
 * that was causing infinite creation of objects. The structure of the how the inventory items relate to
 * one another had to be changed and the following tag had to be added to some of the classes:
 * @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "Id")
 * 
 * Added validation tags to the data entities.
 * 
 * Figured out that Posting through swagger UI isn't working. Will have to setup postman to see if it's just
 * swagger or if it's the way post is constructed.
 * 
 *  * ------- Milestone 4 -------
 * 
 * Setup NoSQL for the inventory data objects.
 * Added seperate classes for each NoSQL data object, NoSQL repos, and NoSQL service calls.
 * Added tests that test the NoSQL service calls.
 * Setup docker compose so that it starts the app and three database in their own containers.
 * 
 * 
 * ------- Nice to Have / To Do List -------
 * 
 * - Add actions in github
 */
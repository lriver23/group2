package edu.depaul.cdm.se452.group2.userAuth;
/*
 * @author Shoeb Khan
 * 
 * ------- Milestone 1 -------
 * 
 * 9/11/2022
 * Package info and readme have been created.
 * 
 * 
 * ------- Milestone 2 -------
 * 
 * Extra Features:
 * 
 * 9/22/2022
 * I created the java classes that will generate the User SQL tables: Authorization, Authentication.
 * 
 * 
 * 9/22/2022
 * I added data.sql file to store sql scripts and tested if insert statements are working and tables are set-up properly.
 * 
 * 9/26/2022
 * I added Tests for both the Authentication and Authoriztion tables
 * For extra features I added NoArgConstructors and AllArgConstructors as extra features.
 * 
 * 
 * 
 * ------- Milestone 3 -------
 * Oct 16, 2022
 * Added services for Authenticatoin and Authorization tables
 * 
 * Oct 20, 2022
 * Added one to one mapping for Authenticatoin and Authorization tables
 * Restructured files in sub folders and added validation services
 * 
 * Oct 21, 2022
 * Added Address and Login entities, repos and thier services and set-up relationships among all entities.
 *  
 * Oct 21, 2022 (Extra Feature)
 * Added extra logic to add rows in other tables with null values for unassigned data when main(Authenticatoin) table is created
 * Added login logic which can verify credentials and set login status in boolean value (Initially false)
 * Added logs
 * 
 * ------- Milestone 4 -------
 * Oct 30, 2022
 * Bug fixed All test files and prepaired the standalone build successfully
 * 
 * Oct 31, 2022 (Extra Feature)
 * Set-up GitHub actions build workflow
 * 
 * Nov 1, 2022 (Extra Feature)
 * Set-up Docker on device and ran standalone container
 * 
 * Nov 2, 2022 (Extra Feature)
 * Set-up Docker hub push via GitHub Actions
 * 
 * Nov 4, 2022
 * Set-up Postgres with docker
 * Change spring version to support MongoDB
 * Set-up mongoDB in compose
 * 
 * Nov 8, 2022
 * Added NoSql Mongo entites, repos and services.
 * Added Repo and service tests for noSql repositories
 * Added sprinng security(Working)
 */

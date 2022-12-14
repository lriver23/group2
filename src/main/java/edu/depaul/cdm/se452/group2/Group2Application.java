package edu.depaul.cdm.se452.group2;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Address;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Authentication;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AddressRepository;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AuthenticationRepo;
//import lombok.var;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class Group2Application {

	public static void main(String[] args) {
		SpringApplication.run(Group2Application.class, args);
	}

	@Bean
	public CommandLineRunner showLogLevel() {
		return (args) -> {
			log.debug("Logging Debug");
			log.info("Logging Info");
			log.warn("Logging Warning");
			log.error("Logging Error");
		};
	}

	@Bean
	public CommandLineRunner testAu(
		AuthenticationRepo AUR,
		AddressRepository addressRepository
	) {
		return (args) -> {
			

			};
	}

}
// docker setup on mac
// add dickerfile in root and docker folder in root which has docker-app.yml
// use diff java version:  eclipse-temurin: 17-jdk-focal
// To run use: docker run -d -p 8080:8080 group2application
//
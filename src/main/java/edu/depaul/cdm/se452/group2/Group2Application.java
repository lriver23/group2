package edu.depaul.cdm.se452.group2;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.depaul.cdm.se452.group2.UserAuth.Address;
import edu.depaul.cdm.se452.group2.UserAuth.AddressRepository;
import edu.depaul.cdm.se452.group2.UserAuth.Authentication;
import edu.depaul.cdm.se452.group2.UserAuth.AuthenticationRepo;
import lombok.var;
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
			Address smkAddress = new Address();
			smkAddress.setLocation("Hyderabad");
			addressRepository.save(smkAddress);

			var sami = new Authentication("Sami", "456", "sami@gmail.com", smkAddress);
			
			long b4 = AUR.count();
			log.info(b4);
			AUR.save(sami);
	
			long after = AUR.count();
			log.info(after);
			};
	}

}
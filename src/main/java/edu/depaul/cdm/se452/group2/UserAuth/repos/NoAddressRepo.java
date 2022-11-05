package edu.depaul.cdm.se452.group2.UserAuth.repos;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.UserAuth.entities.NoAddress;

public interface NoAddressRepo extends MongoRepository<NoAddress, Long> {

    
}

package edu.depaul.cdm.se452.group2.userAuth.repos;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.NoAddress;

public interface NoAddressRepo extends MongoRepository<NoAddress, Long> {

    
}

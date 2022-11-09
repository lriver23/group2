package edu.depaul.cdm.se452.group2.userAuth.repos.nonrelational;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational.NoAddress;

public interface NoAddressRepo extends MongoRepository<NoAddress, Long> {

    
}

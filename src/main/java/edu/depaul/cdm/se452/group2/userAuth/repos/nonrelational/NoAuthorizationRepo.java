package edu.depaul.cdm.se452.group2.userAuth.repos.nonrelational;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational.NoAuthorizationU;

public interface NoAuthorizationRepo extends MongoRepository<NoAuthorizationU, Long> {


    // void deleteById(long user);
    
}

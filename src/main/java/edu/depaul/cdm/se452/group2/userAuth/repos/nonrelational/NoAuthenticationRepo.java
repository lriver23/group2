package edu.depaul.cdm.se452.group2.userAuth.repos.nonrelational;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational.NoAuthentication;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Authentication;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.AuthorizationU;

public interface NoAuthenticationRepo extends MongoRepository<NoAuthentication, String> {

    Optional<NoAuthentication> findById(long id);

    void save(@Valid AuthorizationU user);

    void deleteById(Long id);

    void save(Authentication shoeb);
    
}

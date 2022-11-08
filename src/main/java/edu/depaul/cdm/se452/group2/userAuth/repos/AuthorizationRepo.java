package edu.depaul.cdm.se452.group2.userAuth.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.AuthorizationU;

public interface AuthorizationRepo extends JpaRepository<AuthorizationU, Long> {


    // void deleteById(long user);
    
}

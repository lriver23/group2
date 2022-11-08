package edu.depaul.cdm.se452.group2.userAuth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.Authentication;

public interface AuthenticationRepo extends JpaRepository<Authentication, String> {
    
}

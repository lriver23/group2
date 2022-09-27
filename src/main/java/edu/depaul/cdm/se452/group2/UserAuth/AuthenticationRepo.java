package edu.depaul.cdm.se452.group2.userAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepo extends JpaRepository<Authentication, Long> {
    
}

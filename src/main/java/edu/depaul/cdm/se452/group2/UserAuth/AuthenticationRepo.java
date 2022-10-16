package edu.depaul.cdm.se452.group2.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepo extends JpaRepository<Authentication, String> {
    
}

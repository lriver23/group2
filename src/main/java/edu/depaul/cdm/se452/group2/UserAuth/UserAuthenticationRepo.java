package edu.depaul.cdm.se452.group2.userAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticationRepo extends JpaRepository<UserAuthentication, Long> {
    
}

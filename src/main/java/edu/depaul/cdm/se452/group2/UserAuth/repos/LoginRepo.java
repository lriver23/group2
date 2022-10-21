package edu.depaul.cdm.se452.group2.UserAuth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.depaul.cdm.se452.group2.UserAuth.entities.Login;

public interface LoginRepo extends JpaRepository<Login, Long> {


    // void deleteById(long user);
    
}

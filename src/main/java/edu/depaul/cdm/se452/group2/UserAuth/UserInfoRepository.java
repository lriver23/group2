package edu.depaul.cdm.se452.group2.userAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    
}

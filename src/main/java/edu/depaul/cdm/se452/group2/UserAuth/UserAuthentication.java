package edu.depaul.cdm.se452.group2.userAuth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
//@Table(name = "UserAuthentication")
public class UserAuthentication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "UserName")
    private String u_name;
    
    //@Column(name = "Password")
    private String pwd;

    //@Column(name = "Email")
    private String email;
    

}

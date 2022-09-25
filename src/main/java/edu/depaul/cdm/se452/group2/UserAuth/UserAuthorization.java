
package edu.depaul.cdm.se452.group2.UserAuth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
@Data
@Entity
@Table(name = "UserAuthorization")
public class UserAuthorization {
    //link using primary and secondry keys
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserName")
    private String u_name;
    
    @Column(name = "IsPremium")
    private boolean is_premium;

    @Column(name = "IsSeller")
    private String is_seller;
    

}
*/
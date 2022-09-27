package edu.depaul.cdm.se452.group2.UserAuth;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AuthorizationU")
public class AuthorizationU {
    
    //link using primary and secondry keys
    @Id
    @Column(name = "User_Name")
    private String u_name;
    
    @Column(name = "Is_Premium")
    private boolean is_premium;

    @Column(name = "Is_Seller")
    private boolean is_seller;


}

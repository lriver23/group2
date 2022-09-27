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
@Table(name = "Authorization")
public class Authorization {
    //link using primary and secondry keys
    @Id
    @Column(name = "Name")
    private String u_name;
    
    @Column(name = "Premium")
    private String is_premium;

    @Column(name = "    Seller")
    private String is_seller;
}

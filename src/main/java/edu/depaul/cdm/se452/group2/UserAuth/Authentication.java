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
@Table(name = "Authentication")
public class Authentication {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Name")
    private String u_name;
    
    @Column(name = "Password")
    private String pwd;

    @Column(name = "Email")
    private String email;

}

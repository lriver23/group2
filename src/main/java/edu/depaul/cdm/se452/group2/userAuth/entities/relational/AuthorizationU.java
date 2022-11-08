package edu.depaul.cdm.se452.group2.userAuth.entities.relational;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AuthorizationU")
public class AuthorizationU implements Serializable{
    
    //link using primary and secondry keys

    // @MapsId
    // @OneToOne
    // @JoinColumn(name = "User_Name")
    // private Authentication user; 
    
    // @Id
    // @MapsId
    // @JoinColumn(name = "User_Name")
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @OneToOne
    @JoinColumn(name = "User_Name")
    private Authentication u_name;
    
    @Column(name = "Is_Premium")
    private boolean is_premium;

    @Column(name = "Is_Seller")
    private boolean is_seller;


}

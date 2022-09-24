package edu.depaul.cdm.se452.group2.OrderPlacement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Orders)
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private long Order_id;

    @OneToOne
    @Column(name = "CartNumber")
    private Cart Cart_number;

    @Column(name = "UserId")
    private String User_id;

}

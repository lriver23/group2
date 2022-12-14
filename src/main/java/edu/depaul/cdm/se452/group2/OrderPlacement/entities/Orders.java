package edu.depaul.cdm.se452.group2.OrderPlacement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Orders {

    public Orders(int order_id2, int i, String user_id2) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private long Order_id;

    @OneToOne
    @JoinColumn(name = "Cart_ID")
    private Cart Cart_Id;

    @Column(name = "User_ID")
    private String User_id;

}

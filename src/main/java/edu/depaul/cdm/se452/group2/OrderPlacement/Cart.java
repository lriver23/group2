package edu.depaul.cdm.se452.group2.OrderPlacement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private long Product_id;

    @Column(name = "ProductQuantity")
    private long Product_quantity;

    @Column(name = "OrderID")
    private long Order_id;
}
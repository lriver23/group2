package edu.depaul.cdm.se452.group2.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Stocks")
public class Stock {
    
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @Column(name = "QuantityAvailable")
    private int quantityAvailable;
}

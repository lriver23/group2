package edu.depaul.cdm.se452.group2.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Stocks")
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @Column(name = "QuantityAvailable")
    @Min(value = 0, message = "Item quantities should never be negative")
    @Max(value = Integer.MAX_VALUE, message = "Stock quantities cannot be excessively large")
    private int quantityAvailable;
}

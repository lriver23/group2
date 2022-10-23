package edu.depaul.cdm.se452.group2.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Name")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters long")
    private String name;

    @Column(name = "Description")
    @Size(min = 0, max = 2000, message = "Descriptions can not be longer than 2000 characters long")
    private String description;


    @Column(name = "Price")
    @Positive(message = "The price of a product cannot be negative")
    private double price;

    @ManyToOne
    @JoinColumn(name = "Seller_Id")
    private Seller seller;
    
    @OneToOne
    @JoinColumn(name = "Stock_Id")
    private Stock stock;
    
}

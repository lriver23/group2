package edu.depaul.cdm.se452.group2.inventory.nonrelational.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class NoProduct {
    
    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    @DBRef
    private NoSeller noSeller;

    @DBRef
    private NoStock noStock;
}

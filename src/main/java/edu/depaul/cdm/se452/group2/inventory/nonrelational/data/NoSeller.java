package edu.depaul.cdm.se452.group2.inventory.nonrelational.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Builder;
import lombok.Data;

@Data
@Document("NoSeller")
@Builder
public class NoSeller {
    
    @Id
    private String id;

    private String name;

    private double income;

    @DocumentReference
    private List<NoProduct> NoProducts;
}

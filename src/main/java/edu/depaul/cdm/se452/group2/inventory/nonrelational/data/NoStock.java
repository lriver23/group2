package edu.depaul.cdm.se452.group2.inventory.nonrelational.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("NoStock")
@Builder
public class NoStock {
    @Id
    private String id;

    private int quantityAvailable;
}
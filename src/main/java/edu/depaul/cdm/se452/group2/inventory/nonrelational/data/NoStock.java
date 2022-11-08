package edu.depaul.cdm.se452.group2.inventory.nonrelational.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("NoStock")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoStock {
    @Id
    private String id;

    private int quantityAvailable;
}
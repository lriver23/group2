package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Document("NoCart")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoCart {

    @Id

    private long Cart_id;

    private long Product_quantity;

    private long Order_id;

    @DBRef
    private NoOrders noOrders;
}
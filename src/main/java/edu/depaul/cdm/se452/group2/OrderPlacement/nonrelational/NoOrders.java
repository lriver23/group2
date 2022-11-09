package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("NoOrders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoOrders {

    @Id
    private long Order_id;

    private long Cart_Id;

    private String User_id;

    @DocumentReference
    private List<NoCart> NoCart;

}
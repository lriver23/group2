package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Orders;
import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.NoOrders;

public interface NoOrdersRepo extends MongoRepository<NoOrders, Long> {

    void saveAll(Orders majid);

    // Optional<NoOrders> findById(long order_id);

    // void deleteById(long id);

}

package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos;

import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.NoOrders;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoOrdersRepo extends MongoRepository<NoOrders, String> {

    Optional<NoOrders> findById(long order_id);

    void deleteById(long id);

}

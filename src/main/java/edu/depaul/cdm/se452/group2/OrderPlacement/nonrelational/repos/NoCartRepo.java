package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos;

import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.NoCart;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoCartRepo extends MongoRepository<NoCart, String> {

    Optional<NoCart> findById(long cart_id);

    void deleteById(long id);
}

package edu.depaul.cdm.se452.group2.inventory.nonrelational.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoSellerRepo extends MongoRepository<NoSellerRepo, String> {
    
}

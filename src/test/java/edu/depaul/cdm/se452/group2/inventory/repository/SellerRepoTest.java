package edu.depaul.cdm.se452.group2.inventory.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.inventory.ProductRepository;
import edu.depaul.cdm.se452.group2.inventory.Seller;
import edu.depaul.cdm.se452.group2.inventory.SellerRepository;

@SpringBootTest
public class SellerRepoTest {
    @Autowired
    private SellerRepository sellerRepo;

    @Autowired
    private ProductRepository productRepo;

    @BeforeEach
    public void setup() {
        productRepo.deleteAll();
        sellerRepo.deleteAll();
    }
    
    @Test
    public void testCrud() {
        var seller = new Seller();
        seller.setIncome(4003.12);
        seller.setName("Sales Inc.");

        var initialSellerId = seller.getId();  
        var initialRepoCount = sellerRepo.count();

        sellerRepo.save(seller);
        var endSellerId = seller.getId();
        var endRepoCount = sellerRepo.count();

        assertNotEquals(initialSellerId, endSellerId);
        assertEquals(initialRepoCount + 1, endRepoCount);

        var repoSeller = sellerRepo.findById(endSellerId).orElse(new Seller());
        var updatedIncome = 220.45;
        repoSeller.setIncome(updatedIncome);

        sellerRepo.save(repoSeller);
        var updatedSeller = sellerRepo.findById(endSellerId).orElse(new Seller());
        
        assertNotEquals(seller, updatedSeller);
        assertEquals(updatedSeller.getIncome(), updatedIncome);

        initialRepoCount = sellerRepo.count();
        sellerRepo.delete(updatedSeller);
        endRepoCount = sellerRepo.count();
        assertEquals(initialRepoCount - 1, endRepoCount);
    }   
}

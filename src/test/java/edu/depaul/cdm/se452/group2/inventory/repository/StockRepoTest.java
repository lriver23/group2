package edu.depaul.cdm.se452.group2.inventory.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.inventory.ProductRepository;
import edu.depaul.cdm.se452.group2.inventory.Stock;
import edu.depaul.cdm.se452.group2.inventory.StockRepository;

@SpringBootTest
public class StockRepoTest {
    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private ProductRepository productRepo;

    @BeforeEach
    public void setup() {
        productRepo.deleteAll();
        stockRepo.deleteAll();
    }

    @Test
    public void testCrud() {
        var stock = new Stock();
        stock.setQuantityAvailable(330);
        var initialStockId = stock.getId();  
        var initialRepoCount = stockRepo.count();

        stockRepo.save(stock);
        var endStockId = stock.getId();
        var endRepoCount = stockRepo.count();

        assertNotEquals(initialStockId, endStockId);
        assertEquals(initialRepoCount + 1, endRepoCount);

        var repoStock = stockRepo.findById(endStockId).orElse(new Stock());
        var updatedQuantityAvailable = 180;
        repoStock.setQuantityAvailable(updatedQuantityAvailable);

        stockRepo.save(repoStock);
        var updatedStock = stockRepo.findById(endStockId).orElse(new Stock());
        
        assertNotEquals(stock, updatedStock);
        assertEquals(updatedStock.getQuantityAvailable(), updatedQuantityAvailable);

        initialRepoCount = stockRepo.count();
        stockRepo.delete(updatedStock);
        endRepoCount = stockRepo.count();
        assertEquals(initialRepoCount - 1, endRepoCount);
    }
}

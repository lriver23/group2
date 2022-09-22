package edu.depaul.cdm.se452.group2.inventory.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.inventory.Stock;
import edu.depaul.cdm.se452.group2.inventory.StockRepository;

@SpringBootTest
public class StockRepoTest {
    @Autowired
    private StockRepository repo;

    @Test
    public void testCrud() {
        var stock = new Stock();
        stock.setQuantityAvailable(330);
        var initialStockId = stock.getId();  
        var initialRepoCount = repo.count();

        repo.save(stock);
        var endStockId = stock.getId();
        var endRepoCount = repo.count();

        assertNotEquals(initialStockId, endStockId);
        assertEquals(initialRepoCount + 1, endRepoCount);

        var repoStock = repo.findById(endStockId).orElse(new Stock());
        var updatedQuantityAvailable = 180;
        repoStock.setQuantityAvailable(updatedQuantityAvailable);

        repo.save(repoStock);
        var updatedStock = repo.findById(endStockId).orElse(new Stock());
        
        assertNotEquals(stock, updatedStock);
        assertEquals(updatedStock.getQuantityAvailable(), updatedQuantityAvailable);

        initialRepoCount = repo.count();
        repo.delete(updatedStock);
        endRepoCount = repo.count();
        assertEquals(initialRepoCount - 1, endRepoCount);
    }
}

package edu.depaul.cdm.se452.group2.inventory.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.depaul.cdm.se452.group2.inventory.Stock;

public class StockLombokTest {
    @Test
    public void testLombokFunctions() {
        var stock = new Stock();
        stock.setQuantityAvailable(20);
        var expectedStockToString = "Stock(id=0, quantityAvailable=20)";
        assertEquals(expectedStockToString, stock.toString());
    }
}

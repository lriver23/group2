package edu.depaul.cdm.se452.group2.inventory.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
//import lombok.extern.log4j.Log4j2;

import edu.depaul.cdm.se452.group2.inventory.Stock;

//@Log4j2
public class StockLombokTest {
    @Test
    public void testLombokFunctions() {
        var stock = new Stock();
        stock.setQuantityAvailable(20);
        //log.info("Stock toString: " + stock.toString());

        var expectedStockToString = "Stock(id=0, quantityAvailable=20)";
        assertEquals(expectedStockToString, stock.toString());
    }
}

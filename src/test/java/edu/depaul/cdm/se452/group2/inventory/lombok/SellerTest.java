package edu.depaul.cdm.se452.group2.inventory.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import lombok.extern.log4j.Log4j2;

import edu.depaul.cdm.se452.group2.inventory.Seller;

@Log4j2
public class SellerTest {
    @Test
    public void testLombokFunctions() {
        var seller = new Seller();
        seller.setIncome(200.15);
        seller.setName("Rudyions");
        log.info("Seller toString: " + seller.toString());

        var expectedSellerString = "Seller(id=0, name=Rudyions, income=200.15)";
        assertEquals(expectedSellerString, seller.toString());
    }
}
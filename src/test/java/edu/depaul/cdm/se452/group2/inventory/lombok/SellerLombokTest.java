package edu.depaul.cdm.se452.group2.inventory.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.depaul.cdm.se452.group2.inventory.Seller;

public class SellerLombokTest {
    @Test
    public void testLombokFunctions() {
        var seller = new Seller();
        seller.setIncome(200.15);
        seller.setName("Rudyions");
        var expectedSellerString = "Seller(id=0, name=Rudyions, income=200.15)";
        assertEquals(expectedSellerString, seller.toString());
    }
}
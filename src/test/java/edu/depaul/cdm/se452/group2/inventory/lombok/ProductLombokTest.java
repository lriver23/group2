package edu.depaul.cdm.se452.group2.inventory.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
//import lombok.extern.log4j.Log4j2;

import edu.depaul.cdm.se452.group2.inventory.Product;

//@Log4j2
public class ProductLombokTest {
    @Test
    public void testLombokFunctions() {
        var product = new Product();
        product.setName("Box");
        product.setDescription("A high quality cardboard box.");
        product.setPrice(75.50);
        //log.info("Product toString: " + product.toString());

        var expectedProductToString = "Product(id=0, name=Box, description=A high quality cardboard box., price=75.5)";
        assertEquals(expectedProductToString, product.toString());
    }    
}
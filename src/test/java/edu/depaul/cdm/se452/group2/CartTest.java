package edu.depaul.cdm.se452.group2;

import org.junit.jupiter.api.Test;
import lombok.val;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.OrderPlacement.Cart;
import edu.depaul.cdm.se452.group2.OrderPlacement.CartRepository;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartRepository CartRepo;

    @Test
    public void testCart() {
        val row1 = new Cart(10, 7, 98);
        val row2 = new Cart(20, 8, 100);
        val before = CartRepo.count();
        CartRepo.save(row1);
        CartRepo.save(row2);
        val after = CartRepo.count();
        assertEquals(before + 2, after);
    }
}
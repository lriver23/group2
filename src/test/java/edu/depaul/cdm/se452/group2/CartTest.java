package edu.depaul.cdm.se452.group2;

import org.junit.jupiter.api.Test;
import lombok.val;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.orderPlacement.Cart;
import edu.depaul.cdm.se452.group2.orderPlacement.CartRepository;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartRepository CartRepo;

    @Test
    public void testCart() {
        val row1 = new Cart(111, 555, 2);
        val row2 = new Cart(222, 666, 4);
        val before = CartRepo.count();
        CartRepo.save(row1);
        CartRepo.save(row2);
        val after = CartRepo.count();
        assertEquals(before + 1, after);
    }
}
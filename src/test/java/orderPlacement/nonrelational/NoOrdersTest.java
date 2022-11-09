package orderPlacement.nonrelational;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Cart;
import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Orders;
import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos.NoOrdersRepo;
//import lombok.val;
import edu.depaul.cdm.se452.group2.OrderPlacement.repos.OrdersRepository;

@SpringBootTest
public class NoOrdersTest {
    @Autowired
    private NoOrdersRepo ORR;

    @Test
    public void testOrders() {
        var majid = new Orders(5, 111, "majid");
        var shoeb = new Orders(6, 222, "shoeb");

        var b4 = ORR.count();
        System.out.println(b4);
        ORR.saveAll(majid);
        ORR.saveAll(shoeb);

        var after = ORR.count();

        assertEquals(b4 + 2, after);
    }
}
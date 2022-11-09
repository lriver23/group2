package userAuth.relational;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Address;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Authentication;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AuthenticationRepo;

@SpringBootTest
public class AddressTest {
    @Autowired
    private  Address AUR;
    
    @Test
    public void testAddress() {
        Address smkAddress = new Address();
        smkAddress.setLocation("Delhi");

        
        int b4 = AUR.count();
        AUR.save(smkAddress);
        //AUR.save(majid);
   
        int after = AUR.count();

        assertEquals(b4 + 2, after);
    }
}



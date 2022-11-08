package userAuth.nonrelational;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Address;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Authentication;
import edu.depaul.cdm.se452.group2.userAuth.repos.nonrelational.NoAuthenticationRepo;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AuthenticationRepo;

@SpringBootTest
public class NoAuthenticationTest {
    @Autowired
    private  NoAuthenticationRepo AUR;
    
    @Test
    public void testAuthentication() {
        Address smkAddress = new Address();
        smkAddress.setLocation("Delhi");

        var shoeb = new Authentication();
        var majid = new Authentication();
        
        var b4 = AUR.count();
        AUR.save(shoeb);
        AUR.save(majid);
   
        var after = AUR.count();

        assertEquals(b4 + 2, after);
    }
}



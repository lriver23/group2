package edu.depaul.cdm.se452.group2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.depaul.cdm.se452.group2.UserAuth.Authentication;
import edu.depaul.cdm.se452.group2.UserAuth.AuthenticationRepo;
import lombok.val;

@SpringBootTest
public class AuthenticationTest {
    @Autowired
    private  AuthenticationRepo AUR;
    
    @Test
    public void testAuthentication() {
        val shoeb = new Authentication("Majidd", "emaill", "pwdxfrr");
        val majid = new Authentication("DAL", "Dallas", "TX");
        
        val b4 = AUR.count();
        AUR.save(shoeb);
        AUR.save(majid);
   
        val after = AUR.count();

        assertEquals(b4 + 2, after);
    }
}



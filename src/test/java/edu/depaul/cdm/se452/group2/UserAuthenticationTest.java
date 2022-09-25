package edu.depaul.cdm.se452.group2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.UserAuth.UserAuthentication;
import edu.depaul.cdm.se452.group2.UserAuth.UserAuthenticationRepo;
import lombok.val;

@SpringBootTest
public class UserAuthenticationTest {
    @Autowired
    private  UserAuthenticationRepo AUR;
    
    @Test
    public void testUserAuthentication() {
        val shoeb = new UserAuthentication("Majidd", "emaill", "pwdxfrr");
        val majid = new UserAuthentication("DAL", "Dallas", "TX");
        
        val b4 = AUR.count();
        AUR.save(shoeb);
        AUR.save(majid);
   
        val after = AUR.count();

        assertEquals(b4 + 2, after);
    }
}



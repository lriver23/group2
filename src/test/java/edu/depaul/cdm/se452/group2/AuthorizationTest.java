package edu.depaul.cdm.se452.group2;
import org.junit.jupiter.api.Test;
import lombok.val;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.userAuth.AuthorizationRepo;
import edu.depaul.cdm.se452.group2.userAuth.AuthorizationU;

@SpringBootTest
public class AuthorizationTest {
    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Test
    public void testAuthorization() {
        val row1 = new AuthorizationU("Shoeb",true, true );
        val row2 = new AuthorizationU("Majid",false, false );
        val before = authorizationRepo.count();
        authorizationRepo.save(row1);
        authorizationRepo.save(row2);
        val after = authorizationRepo.count();
        assertEquals(before + 2, after);
    }
}

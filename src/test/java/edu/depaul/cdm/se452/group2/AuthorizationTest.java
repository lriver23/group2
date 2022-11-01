package edu.depaul.cdm.se452.group2;
import org.junit.jupiter.api.Test;
//import lombok.var;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.group2.UserAuth.repos.AuthenticationRepo;
import edu.depaul.cdm.se452.group2.UserAuth.repos.AuthorizationRepo;
import edu.depaul.cdm.se452.group2.UserAuth.entities.Authentication;
import edu.depaul.cdm.se452.group2.UserAuth.entities.AuthorizationU;

@SpringBootTest
public class AuthorizationTest {

    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Autowired
    AuthenticationRepo authenticationRepo;

    @Test
    public void testAuthorization() {
        var row1 = new AuthorizationU();
        var auth1 = new Authentication();
        auth1.setU_name("John");
        authenticationRepo.save(auth1);
        row1.setU_name(auth1);

        var before = authorizationRepo.count();
        authorizationRepo.save(row1);
        
        var after = authorizationRepo.count();
        assertEquals(before + 1, after);
    }
}

package edu.depaul.cdm.se452.group2.userAuth.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group2.userAuth.entities.Authentication;
import edu.depaul.cdm.se452.group2.userAuth.entities.AuthorizationU;
import edu.depaul.cdm.se452.group2.userAuth.entities.Login;
import edu.depaul.cdm.se452.group2.userAuth.repos.AuthenticationRepo;
import edu.depaul.cdm.se452.group2.userAuth.repos.AuthorizationRepo;
import edu.depaul.cdm.se452.group2.userAuth.repos.LoginRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication",description = "Allows manipulation of Authentication Data")
@Log4j2
public class AuthenticationService {
    @Autowired
    private AuthenticationRepo repo;

    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Autowired
    private LoginRepo loginRepo;
    
    @GetMapping
    @Operation(summary = "Returns all the Auth data")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=Authentication.class))})
    public List<Authentication> list() {
        log.traceEntry("Enter List");
        var retval = repo.findAll();
        log.traceEntry("Exit List");
        return repo.findAll();
    }
    @PostMapping()
    @Operation(summary = "Add new user information")
    public void save(@RequestBody Authentication authentication){
        log.traceEntry("enter save");
        repo.save(authentication);

        var aU = new AuthorizationU();
        aU.setU_name(authentication);
        aU.set_seller(false);
        aU.set_premium(false);
        authorizationRepo.save(aU);

        var lg = new Login();
        lg.setU_name(authentication);
        lg.setLoggedIn(false);
        loginRepo.save(lg);

        log.traceEntry("exit save");
    }

    @Operation(summary = "Delete user information")
    @DeleteMapping("{user}")
    public void delete(@PathVariable("user") String user) {
        log.traceEntry("Enter delete", user);
        repo.deleteById(user);
        log.traceExit("Exit delete");
    }

    @PutMapping
    @Operation(summary = "Update user information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Authentication.class))})
    public ResponseEntity<String> put(@Valid @RequestBody Authentication user) {
        log.traceEntry("Enter put", user);
        if(repo.findById(user.getU_name()).isPresent()){
            repo.save(user);
            return ResponseEntity.ok("User updated");
        } else{
            log.traceExit("Exit user update");
            return ResponseEntity.badRequest().build();
        }
        
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the User and returns the User id")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Authentication authentication) {
        log.traceEntry("enter save", authentication);
        repo.save(authentication);
        log.traceExit("exit save", authentication);
        return ResponseEntity.ok("new id is " + authentication.getU_name());
    }
    
}
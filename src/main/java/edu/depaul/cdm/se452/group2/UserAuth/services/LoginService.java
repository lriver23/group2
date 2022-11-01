
package edu.depaul.cdm.se452.group2.UserAuth.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;
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

import edu.depaul.cdm.se452.group2.UserAuth.entities.Authentication;
import edu.depaul.cdm.se452.group2.UserAuth.entities.Login;
import edu.depaul.cdm.se452.group2.UserAuth.repos.AuthenticationRepo;
import edu.depaul.cdm.se452.group2.UserAuth.repos.AuthorizationRepo;
import edu.depaul.cdm.se452.group2.UserAuth.repos.LoginRepo;
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
@RequestMapping("/api/login")
@Tag(name = "Login",description = "Allows exixting users to login")
@Log4j2
public class LoginService {
    @Autowired
    private LoginRepo repo;

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @GetMapping
    @Operation(summary = "Returns all the logged in users")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation= Login.class))})
    public List<Login> list() {
        log.traceEntry("Enter List");
        var retval = repo.findAll();
        log.traceEntry("Exit List");
        return repo.findAll();
    }

    @PostMapping()
    @Operation(summary = "Login new user")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Login.class))})
    public void save(@RequestBody Login user){
        
        log.traceEntry("enter save");  
        if(repo.findById(user.getId()).isPresent()){
            user.setLoggedIn(true);
            repo.save(user);
            log.info("user logged in");
            //return ResponseEntity.ok("new id is " + user.getU_name());
        }
        else log.info("user not found");
        log.traceEntry("exit save");
    }

    // @DeleteMapping
    // @Operation(summary = "Delete user Authorization information")
    // @ApiResponse(responseCode = "200", description = "valid response",
    // content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    // public void delete(Long id) {
    //     log.traceEntry("Enter delete", id);
    //     repo.deleteById(id);
    //     log.traceExit("Exit delete");
    // }

    // @PutMapping
    // @Operation(summary = "Update user information")
    // @ApiResponse(responseCode = "200", description = "valid response",
    // content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    // public ResponseEntity<String> put(@Valid @RequestBody AuthorizationU user) {
    //     log.traceEntry("Enter put", user);
    //     if(repo.findById(user.getId()).isPresent()){
    //         repo.save(user);
    //         return ResponseEntity.ok("User updated");
    //     } else{
    //         log.traceExit("Exit user update");
    //         return ResponseEntity.badRequest().build();
    //     }
        
    // }

    // @PostMapping("/validated")
    // @Operation(summary = "Save the User and returns the User id")
    // public ResponseEntity<String> validatedSave(@Valid @RequestBody Authentication authentication) {
    //     log.traceEntry("enter save", authentication);
    //     repo.save(authentication);
    //     log.traceExit("exit save", authentication);
    //     return ResponseEntity.ok("new id is " + authentication.getU_name());
    // }
    
}
package edu.depaul.cdm.se452.group2.userAuth.services.nonrelational;

import java.util.List;

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

import edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational.NoAuthentication;
import edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational.NoAuthorizationU;
import edu.depaul.cdm.se452.group2.userAuth.entities.relational.AuthorizationU;
import edu.depaul.cdm.se452.group2.userAuth.repos.nonrelational.NoAuthenticationRepo;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AuthenticationRepo;
import edu.depaul.cdm.se452.group2.userAuth.repos.relational.AuthorizationRepo;
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
@RequestMapping("/api/noauthorization")
@Tag(name = "NoAuthorizationU",description = "Allows manipulation of Authorization Data")
@Log4j2
public class NoAuthorizationService {
    @Autowired
    private NoAuthenticationRepo repo;

    
    @GetMapping
    @Operation(summary = "Returns all the Authorization data")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoAuthorizationU.class))})
    public List<NoAuthentication> list() {
        log.traceEntry("Enter List");
        var retval = repo.findAll();
        log.traceEntry("Exit List");
        return repo.findAll();
    }
    @PostMapping()
    @Operation(summary = "Add new user information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoAuthorizationU.class))})
    public void save(@RequestBody AuthorizationU authorizationU){
        log.traceEntry("enter save");  
        repo.save(authorizationU);
        log.traceEntry("exit save");
    }

    @DeleteMapping
    @Operation(summary = "Delete user Authorization information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoAuthorizationU.class))})
    public void delete(Long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    @PutMapping
    @Operation(summary = "Update user information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoAuthorizationU.class))})
    public ResponseEntity<String> put(@Valid @RequestBody AuthorizationU user) {
        log.traceEntry("Enter put", user);
        if(repo.findById(user.getId()).isPresent()){
            repo.save(user);
            return ResponseEntity.ok("User updated");
        } else{
            log.traceExit("Exit user update");
            return ResponseEntity.badRequest().build();
        }
        
    }

    // @PostMapping("/validated")
    // @Operation(summary = "Save the User and returns the User id")
    // public ResponseEntity<String> validatedSave(@Valid @RequestBody Authentication authentication) {
    //     log.traceEntry("enter save", authentication);
    //     repo.save(authentication);
    //     log.traceExit("exit save", authentication);
    //     return ResponseEntity.ok("new id is " + authentication.getU_name());
    // }
    
}
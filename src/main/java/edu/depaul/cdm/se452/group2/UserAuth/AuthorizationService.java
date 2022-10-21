package edu.depaul.cdm.se452.group2.UserAuth;

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
@RequestMapping("/api/authorization")
@Tag(name = "AuthorizationU",description = "Allows manipulation of Authorization Data")
@Log4j2
public class AuthorizationService {
    @Autowired
    private AuthorizationRepo repo;
    
    @GetMapping
    @Operation(summary = "Returns all the Authorization data")
    @ApiResponse(responseCode = "200", description = "valid response", 
        content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    public List<AuthorizationU> list() {
        log.traceEntry("Enter List");
        var retval = repo.findAll();
        log.traceEntry("Exit List");
        return repo.findAll();
    }
    @PostMapping()
    @Operation(summary = "Add new user information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    public void save(@RequestBody AuthorizationU authorizationU){
        log.traceEntry("enter save");

        repo.save(authorizationU);
        log.traceEntry("exit save");
    }

    @DeleteMapping("{user}")
    @Operation(summary = "Delete user Authorization information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    public void delete(@PathVariable("user") String user) {
        log.traceEntry("Enter delete", user);
        repo.deleteById(user);
        log.traceExit("Exit delete");
    }

    @PutMapping
    @Operation(summary = "Update user information")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=AuthorizationU.class))})
    public ResponseEntity<String> put(@Valid @RequestBody AuthorizationU user) {
        log.traceEntry("Enter put", user);
        if(repo.findById(user.getU_name()).isPresent()){
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
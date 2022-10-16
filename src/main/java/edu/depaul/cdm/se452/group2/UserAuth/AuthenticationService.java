package edu.depaul.cdm.se452.group2.UserAuth;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group2.UserAuth.Authentication;
import edu.depaul.cdm.se452.group2.UserAuth.AuthenticationRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication",description = "LOL SO MESSED UP")
@Log4j2
public class AuthenticationService {
    @Autowired
    private AuthenticationRepo repo;
    
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
    @Operation(summary = "Add user Info")
    public void save(@RequestBody Authentication authentication){
        log.traceEntry("enter save");
        repo.save(authentication);
        log.traceEntry("exit save");
    }

    @DeleteMapping("{code}")
    public void delete(@PathVariable("code") String code) {
        log.traceEntry("Enter delete", code);
        repo.deleteById(code);
        log.traceExit("Exit delete");
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
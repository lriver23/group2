
package edu.depaul.cdm.se452.group2.UserAuth.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group2.UserAuth.entities.Address;
import edu.depaul.cdm.se452.group2.UserAuth.repos.AddressRepository;
import edu.depaul.cdm.se452.group2.inventory.util.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
//import lombok.var;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Address", description = "Allows manipulation of Addresses")
@Log4j2
public class AddressService{
    @Autowired
    private AddressRepository repo;
    
    @GetMapping
    @Operation(summary = "Returns a complete list of all Addresses")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Address.class))})
    public List<Address> getAddress() {
        log.traceEntry("Enter getAddress");
        var foundAddresses = repo.findAll();
        log.traceExit("Exit getAddress", foundAddresses);
        return foundAddresses;
    }

    public long saveAddress(@NonNull Address address) {
        log.traceEntry("Enter saveAddress");
        repo.save(address);
        log.traceExit("Exit saveHistory", address);
        return address.getId();
    }

    @PostMapping
    @Operation(summary = "Adds address and returns its id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Address.class))})
    public ResponseEntity<String> addAddress(@Valid @RequestBody Address address) {
        log.traceEntry("Enter postAddress");
        var savedAddressId = saveAddress(address);
        log.traceExit("Exit postAddress", saveAddress(address));
        return ResponseEntity.ok("Address added from search, new id is " + savedAddressId);
    }


    // @PutMapping
    // @Operation(summary = "Updates search history item given changed search item from the database")
    // @ApiResponse(responseCode = "200", description = "valid response",
    // content = {@Content(mediaType="application/json", schema=@Schema(implementation=History.class))})
    // public ResponseEntity<String> putHistory(@Valid @RequestBody History history) {
    //     log.traceEntry("Enter putHistory");

    //     if(repo.findById(history.getId()).isPresent()) {
    //         saveHistory(history);
    //         log.traceExit("Exit postHistory", history);
    //         return ResponseEntity.ok("History updated");
    //     }
    //     else {
    //         log.traceExit("Exit putHistory", history);
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    @DeleteMapping
    @Operation(summary = "Deletes an address when given its id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Address.class))})
    public void deleteAddress(long id) {
        log.traceEntry("Enter deleteAddress", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteAddress");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

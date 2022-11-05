package edu.depaul.cdm.se452.group2.inventory.nonrelational.service;

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

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoSeller;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoSellerRepo;
import edu.depaul.cdm.se452.group2.inventory.util.ApiUtils;
import edu.depaul.cdm.se452.group2.inventory.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/sellers")
@Tag(name = "Sellers", description = "Allows manipulation of Seller database items")
@Log4j2
public class NoSellerService {
    @Autowired
    private NoSellerRepo repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all noSellers")
    @ApiResponse(responseCode = "200", description = "valid respons",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoSeller.class))})
    public List<NoSeller> getAllNoSellers() {
        log.traceEntry("Enter getAllNoSellers");
        var foundNoSellers = repo.findAll();
        log.traceExit("Exit getAllNoSellers", foundNoSellers);
        return foundNoSellers;
    }

    public String saveNoSeller(@NonNull NoSeller noSeller) {
        log.traceEntry("Enter saveNoSeller");
        repo.save(noSeller);
        log.traceExit("Exit saveNoSeller", noSeller);
        return noSeller.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a noSeller and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoSeller.class))})
    public ResponseEntity<String> postNoSeller(@Valid @RequestBody NoSeller noSeller) {
        log.traceEntry("Enter postNoSeller");
        var savedNoSellerId = saveNoSeller(noSeller);
        log.traceExit("Exit postNoSeller", saveNoSeller(noSeller));
        return ResponseEntity.ok("NoSeller added, new id is " + savedNoSellerId);
    }


    @PutMapping
    @Operation(summary = "Updates a noSeller when given a changed noSeller from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoSeller.class))})
    public ResponseEntity<String> putNoSeller(@Valid @RequestBody NoSeller noSeller) {
        log.traceEntry("Enter putNoSeller");

        if(repo.findById(noSeller.getId()).isPresent()) {
            saveNoSeller(noSeller);
            log.traceExit("Exit putNoSeller", noSeller);
            return ResponseEntity.ok(ResponseUtil.constructSuccessWithObject(noSeller, "noSeller"));
            
        }
        else {
            log.traceExit("Exit postNoSeller", noSeller);        
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(noSeller.getId(), "noSeller"));
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a noSeller when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoSeller.class))})
    public ResponseEntity<String> deleteSeller(String id) {
        log.traceEntry("Enter deleteNoSeller", id);

        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteNoSeller");
            return ResponseEntity.ok(ResponseUtil.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteNoSeller");
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(id, "noSeller"));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

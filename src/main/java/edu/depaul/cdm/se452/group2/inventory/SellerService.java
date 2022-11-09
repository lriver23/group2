package edu.depaul.cdm.se452.group2.inventory;

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
@RequestMapping("/api/sellers")
@Tag(name = "Sellers", description = "Allows manipulation of Seller database items")
@Log4j2
public class SellerService {
    @Autowired
    private SellerRepository repo;


    @GetMapping
    @Operation(summary = "Returns a complete list of all sellers")
    @ApiResponse(responseCode = "200", description = "valid respons",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Seller.class))})
    public List<Seller> getAllSellers() {
        log.traceEntry("Enter getAllSellers");
        var foundSellers = repo.findAll();
        log.traceExit("Exit getAllSellers", foundSellers);
        return foundSellers;
    }

    public long saveSeller(@NonNull Seller seller) {
        log.traceEntry("Enter saveSeller");
        repo.save(seller);
        log.traceExit("Exit saveSeller", seller);
        return seller.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a seller and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Seller.class))})
    public ResponseEntity<String> postSeller(@Valid @RequestBody Seller seller) {
        log.traceEntry("Enter postSeller");
        var savedSellerId = saveSeller(seller);
        log.traceExit("Exit postSeller", saveSeller(seller));
        return ResponseEntity.ok("Seller added, new id is " + savedSellerId);
    }


    @PutMapping
    @Operation(summary = "Updates a seller when given a changed seller from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Seller.class))})
    public ResponseEntity<String> putSeller(@Valid @RequestBody Seller seller) {
        log.traceEntry("Enter putSeller");

        if(repo.findById(seller.getId()).isPresent()) {
            saveSeller(seller);
            log.traceExit("Exit putSeller", seller);
            return ResponseEntity.ok(SellerServiceResponse.constructSuccessWithSeller(seller));
        }
        else {
            log.traceExit("Exit postSeller", seller);
            return ResponseEntity.badRequest().body(SellerServiceResponse.constructErrorNoSellerExists(seller.getId()));
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a seller when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Seller.class))})
    public ResponseEntity<String> deleteSeller(long id) {
        log.traceEntry("Enter deleteSeller", id);

        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteSeller");
            return ResponseEntity.ok(SellerServiceResponse.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteSeller");
            return ResponseEntity.badRequest().body(SellerServiceResponse.constructErrorNoSellerExists(id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

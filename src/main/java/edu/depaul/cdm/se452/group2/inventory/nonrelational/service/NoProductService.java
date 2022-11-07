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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoProduct;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoProductRepo;
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
@RequestMapping("/api/noproducts")
@Tag(name = "NoProducts", description = "Allows manipulation of NoProduct database items")
@Log4j2
public class NoProductService {
    
    @Autowired
    private NoProductRepo repo;
    
    @GetMapping
    @Operation(summary = "Returns a complete list of all noProducts")
    @ApiResponse(responseCode = "200", description = "valid respons",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoProduct.class))})
    public List<NoProduct> getAllNoProducts() {
        log.traceEntry("Enter getAllNoProducts");
        List<NoProduct> foundNoProducts = repo.findAll();
        log.traceExit("Exit getAllNoProducts", foundNoProducts);
        return foundNoProducts;
    }

    public String saveNoProduct(@NonNull NoProduct noProduct) {
        log.traceEntry("Enter saveNoProduct");
        repo.save(noProduct);
        log.traceExit("Exit saveNoProduct", noProduct);
        return noProduct.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a noProduct and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoProduct.class))})
    public ResponseEntity<String> postNoProduct(@Valid @RequestBody NoProduct noProduct) {
        log.traceEntry("Enter postNoProduct");
        String savedNoProductId = saveNoProduct(noProduct);
        log.traceExit("Exit postNoProduct", saveNoProduct(noProduct));
        return ResponseEntity.ok("NoProduct added, new id is " + savedNoProductId);
    }


    @PutMapping
    @Operation(summary = "Updates a noProduct when given a changed noProduct from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoProduct.class))})
    public ResponseEntity<String> putNoProduct(@Valid @RequestBody NoProduct noProduct) {
        log.traceEntry("Enter putNoProduct");

        if(repo.findById(noProduct.getId()).isPresent()) {
            saveNoProduct(noProduct);
            log.traceExit("Exit postNoProduct", noProduct);
            return ResponseEntity.ok(ResponseUtil.constructSuccessWithObject(noProduct, "noProduct"));
        }
        else {
            log.traceExit("Exit putProduct", noProduct);
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(noProduct.getId(), "noProduct"));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a noProduct when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoProduct.class))})
    public ResponseEntity<String> deleteNoProduct(@PathVariable String id) {
        log.traceEntry("Enter deleteNoProduct", id);

        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteNoProduct");
            return ResponseEntity.ok(ResponseUtil.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteNoProduct");
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(id, "noProduct"));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

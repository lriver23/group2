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
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Allows manipulation of Product database items")
@Log4j2
public class ProductService {
    @Autowired
    private ProductRepository repo;
    
    @GetMapping
    @Operation(summary = "Returns a complete list of all products")
    @ApiResponse(responseCode = "200", description = "valid respons",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Product.class))})
    public List<Product> getAllProducts() {
        log.traceEntry("Enter getAllProducts");
        var foundProducts = repo.findAll();
        log.traceExit("Exit getAllProducts", foundProducts);
        return foundProducts;
    }

    public long saveProduct(@NonNull Product product) {
        log.traceEntry("Enter saveProduct");
        repo.save(product);
        log.traceExit("Exit saveProduct", product);
        return product.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a product and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Product.class))})
    public ResponseEntity<String> postProduct(@Valid @RequestBody Product product) {
        log.traceEntry("Enter postProduct");
        var savedProductId = saveProduct(product);
        log.traceExit("Exit postProduct", saveProduct(product));
        return ResponseEntity.ok("Product added, new id is " + savedProductId);
    }


    @PutMapping
    @Operation(summary = "Updates a product when given a changed product from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Product.class))})
    public ResponseEntity<String> putProduct(@Valid @RequestBody Product product) {
        log.traceEntry("Enter putProduct");

        if(repo.findById(product.getId()).isPresent()) {
            saveProduct(product);
            log.traceExit("Exit postProduct", product);
            return ResponseEntity.ok(ProductServiceResponse.constructSuccessWithProduct(product));
        }
        else {
            log.traceExit("Exit putProduct", product);
            return ResponseEntity.badRequest().body(ProductServiceResponse.constructErrorNoProductExists(product.getId()));
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a product when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Product.class))})
    public ResponseEntity<String> deleteProduct(long id) {
        log.traceEntry("Enter deleteProduct", id);


        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteProduct");
            return ResponseEntity.ok(ProductServiceResponse.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteProduct");
            return ResponseEntity.badRequest().body(ProductServiceResponse.constructErrorNoProductExists(id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

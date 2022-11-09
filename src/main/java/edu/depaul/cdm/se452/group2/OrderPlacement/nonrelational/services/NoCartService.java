package edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.services;

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

import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.NoCart;
import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos.NoCartRepo;
//import edu.depaul.cdm.se452.group2.OrderPlacement.repos.CartRepository;
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
@RequestMapping("/api/nocart")
@Tag(name = "NoCart", description = "Allows Buyers to add items to the Nocart")
@Log4j2
public class NoCartService {
    @Autowired
    private NoCartRepo repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all nocarts")
    @ApiResponse(responseCode = "200", description = "valid respons", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoCart.class)) })
    public List<NoCart> getAllNoCarts() {
        log.traceEntry("Enter getAllNoCarts");
        var foundNoCarts = repo.findAll();
        log.traceExit("Exit getAllCarts", foundNoCarts);
        return foundNoCarts;
    }

    public long saveNoCarts(@NonNull NoCart c) {
        log.traceEntry("Enter saveNoCart");
        repo.save(c);
        log.traceExit("Exit saveNoCart", c);
        return c.getCart_id();
    }

    @PostMapping
    @Operation(summary = "Adds nocart and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoCart.class)) })
    public ResponseEntity<String> postNoOrder(@Valid @RequestBody NoCart Nocart) {
        log.traceEntry("Enter postCart");
        var savedNoCartId = saveNoCarts(Nocart);
        log.traceExit("Exit postNoCarts");
        return ResponseEntity.ok("NoCart added, new id is " + savedNoCartId);
    }

    @PutMapping
    @Operation(summary = "Updates a nocart ")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoCart.class)) })
    public ResponseEntity<String> putNoCart(@Valid @RequestBody NoCart Nocart) {
        log.traceEntry("Enter putNoCart");

        if (repo.findById(Nocart.getCart_id()).isPresent()) {
            saveNoCarts(Nocart);
            log.traceExit("Exit putCart");
            return ResponseEntity.ok("NoCart updated");
        } else {
            log.traceExit("Exit putNoCart");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a nocart when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoCart.class)) })
    public void deleteSeller(long id) {
        log.traceEntry("Enter deleteNoCart", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteNoCart");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

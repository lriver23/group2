package edu.depaul.cdm.se452.group2.OrderPlacement.services;

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

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Cart;
import edu.depaul.cdm.se452.group2.OrderPlacement.repos.CartRepository;
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
@RequestMapping("/api/cart")
@Tag(name = "Carts", description = "Allows Buyers to add items to the cart")
@Log4j2
public class CartServices {
    @Autowired
    private CartRepository repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all carts")
    @ApiResponse(responseCode = "200", description = "valid respons", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) })
    public List<Cart> getAllCarts() {
        log.traceEntry("Enter getAllCarts");
        var foundCarts = repo.findAll();
        log.traceExit("Exit getAllCarts", foundCarts);
        return foundCarts;
    }

    public long saveCarts(@NonNull Cart c) {
        log.traceEntry("Enter saveCart");
        repo.save(c);
        log.traceExit("Exit saveCart", c);
        return c.getCart_id();
    }

    @PostMapping
    @Operation(summary = "Adds cart and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) })
    public ResponseEntity<String> postOrder(@Valid @RequestBody Cart cart) {
        log.traceEntry("Enter postCart");
        var savedCartId = saveCarts(cart);
        log.traceExit("Exit postCarts");
        return ResponseEntity.ok("Cart added, new id is " + savedCartId);
    }

    @PutMapping
    @Operation(summary = "Updates a cart ")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) })
    public ResponseEntity<String> putCart(@Valid @RequestBody Cart cart) {
        log.traceEntry("Enter putOrder");

        if (repo.findById(cart.getCart_id()).isPresent()) {
            saveCarts(cart);
            log.traceExit("Exit putCart");
            return ResponseEntity.ok("Cart updated");
        } else {
            log.traceExit("Exit putCart");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a cart when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) })
    public void deleteSeller(long id) {
        log.traceEntry("Enter deleteCart", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteCart");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

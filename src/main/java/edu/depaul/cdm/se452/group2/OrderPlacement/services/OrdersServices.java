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

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Orders;
import edu.depaul.cdm.se452.group2.OrderPlacement.repos.OrdersRepository;
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
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Allows Buyers to order items")
@Log4j2
public class OrdersServices {
    @Autowired
    private OrdersRepository repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all orders")
    @ApiResponse(responseCode = "200", description = "valid respons", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)) })
    public List<Orders> getAllOrders() {
        log.traceEntry("Enter getAllOrders");
        var foundOrders = repo.findAll();
        log.traceExit("Exit getAllOrders", foundOrders);
        return foundOrders;
    }

    public long saveOrders(@NonNull Orders order) {
        log.traceEntry("Enter saveOrder");
        repo.save(order);
        log.traceExit("Exit saveOrder", order);
        return order.getOrder_id();
    }

    @PostMapping
    @Operation(summary = "Adds order and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)) })
    public ResponseEntity<String> postOrder(@Valid @RequestBody Orders order) {
        log.traceEntry("Enter postOrder");
        var savedOrderId = saveOrders(order);
        log.traceExit("Exit postOrders");
        return ResponseEntity.ok("Order added, new id is " + savedOrderId);
    }

    @PutMapping
    @Operation(summary = "Updates a order ")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)) })
    public ResponseEntity<String> putOrder(@Valid @RequestBody Orders order) {
        log.traceEntry("Enter putOrder");

        if (repo.findById(order.getOrder_id()).isPresent()) {
            saveOrders(order);
            log.traceExit("Exit putOrder");
            return ResponseEntity.ok("Order updated");
        } else {
            log.traceExit("Exit putOrder");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a order when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)) })
    public void deleteSeller(long id) {
        log.traceEntry("Enter deleteOrdder", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteOrder");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

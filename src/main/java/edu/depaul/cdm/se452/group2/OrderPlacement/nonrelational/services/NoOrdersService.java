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

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Orders;
import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.NoOrders;
import edu.depaul.cdm.se452.group2.OrderPlacement.nonrelational.repos.NoOrdersRepo;
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
@RequestMapping("/api/noorders")
@Tag(name = "NoOrders", description = "Allows Buyers to Noorder items")
@Log4j2
public class NoOrdersService {
    @Autowired
    private NoOrdersRepo repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all noorders")
    @ApiResponse(responseCode = "200", description = "valid respons", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoOrders.class)) })
    public List<NoOrders> getAllNoOrders() {
        log.traceEntry("Enter getAllOrders");
        var foundNoOrders = repo.findAll();
        log.traceExit("Exit getAllOrders", foundNoOrders);
        return foundNoOrders;
    }

    public long saveNoOrders(@NonNull NoOrders Noorder) {
        log.traceEntry("Enter saveNoOrder");
        repo.save(Noorder);
        log.traceExit("Exit saveOrder", Noorder);
        return Noorder.getOrder_id();
    }

    @PostMapping
    @Operation(summary = "Adds Noorder and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoOrders.class)) })
    public ResponseEntity<String> postNoOrder(@Valid @RequestBody NoOrders Noorder) {
        log.traceEntry("Enter postNoOrder");
        var savedNoOrderId = saveNoOrders(Noorder);
        log.traceExit("Exit postNoOrders");
        return ResponseEntity.ok("NoOrder added, new id is " + savedNoOrderId);
    }

    @PutMapping
    @Operation(summary = "Updates a Noorder ")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoOrders.class)) })
    public ResponseEntity<String> putNoOrder(@Valid @RequestBody NoOrders Noorder) {
        log.traceEntry("Enter putNoOrder");

        if (repo.findById(Noorder.getOrder_id()).isPresent()) {
            saveNoOrders(Noorder);
            log.traceExit("Exit putNoOrder");
            return ResponseEntity.ok("NoOrder updated");
        } else {
            log.traceExit("Exit putNoOrder");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a Noorder when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoOrders.class)) })
    public void deleteNoOrder(long id) {
        log.traceEntry("Enter deleteNoOrdder", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteNoOrder");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

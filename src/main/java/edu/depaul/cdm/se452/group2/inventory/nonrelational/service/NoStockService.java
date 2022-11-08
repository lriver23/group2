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

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoStock;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoStockRepo;
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
@RequestMapping("/api/nostocks")
@Tag(name = "NoStocks", description = "Allows manipulation of NoStock database items")
@Log4j2
public class NoStockService {

    @Autowired
    private NoStockRepo repo;

    @GetMapping
    @Operation(summary = "Returns a complete list of all stocks from NoSQL")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoStock.class))})
    public List<NoStock> getAllNoStocks() {
        log.traceEntry("Enter getAllNoStocks");
        List<NoStock> foundNoStocks = repo.findAll();
        log.traceExit("Exit getAllNoStocks", foundNoStocks);
        return foundNoStocks;
    }

    public String saveNoStock(@NonNull NoStock noStock) {
        log.traceEntry("Enter saveNoStock");
        repo.save(noStock);
        log.traceExit("Exit saveStock", noStock);
        return noStock.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a noStock and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoStock.class))})
    public ResponseEntity<String> postStock(@Valid @RequestBody NoStock noStock) {
        log.traceEntry("Enter postNoStock");
        String savedNoStockId = saveNoStock(noStock);
        log.traceExit("Exit postNoStock", saveNoStock(noStock));
        return ResponseEntity.ok("Stock added, new id is " + savedNoStockId);
    }


    @PutMapping
    @Operation(summary = "Updates a noStock when given a changed noStock from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoStock.class))})
    public ResponseEntity<String> putNoStock(@Valid @RequestBody NoStock noStock) {
        log.traceEntry("Enter postStock");

        if(repo.findById(noStock.getId()).isPresent()) {
            saveNoStock(noStock);
            log.traceExit("Exit putStock", noStock);
            return ResponseEntity.ok(ResponseUtil.constructSuccessWithObject(noStock, "stock"));
        }
        else {
            log.traceExit("Exit putStock", noStock);
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(noStock.getId(), "stock"));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a noStock when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=NoStock.class))})
    public ResponseEntity<String> deleteNoStock(@PathVariable String id) {
        log.traceEntry("Enter deleteStock", id);

        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteStock");
            return ResponseEntity.ok(ResponseUtil.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteStock");
            return ResponseEntity.badRequest().body(ResponseUtil.constructErrorNoIdExists(id + "", "stock"));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}



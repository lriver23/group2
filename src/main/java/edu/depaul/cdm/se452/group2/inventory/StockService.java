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
import lombok.var;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stocks", description = "Allows manipulation of Stock database items")
@Log4j2
public class StockService {
    @Autowired
    private StockRepository repo;
    
    @GetMapping
    @Operation(summary = "Returns a complete list of all stocks")
    @ApiResponse(responseCode = "200", description = "valid respons",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Stock.class))})
    public List<Stock> getAllStocks() {
        log.traceEntry("Enter getAllStocks");
        var foundStocks = repo.findAll();
        log.traceExit("Exit getAllStocks", foundStocks);
        return foundStocks;
    }

    public long saveStocks(@NonNull Stock stock) {
        log.traceEntry("Enter saveStock");
        repo.save(stock);
        log.traceExit("Exit saveStock", stock);
        return stock.getId();
    }

    @PostMapping
    @Operation(summary = "Adds a stock and returns its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Stock.class))})
    public ResponseEntity<String> postStock(@Valid @RequestBody Stock stock) {
        log.traceEntry("Enter postStock");
        var savedStockId = saveStocks(stock);
        log.traceExit("Exit postStock", saveStocks(stock));
        return ResponseEntity.ok("Stock added, new id is " + savedStockId);
    }


    @PutMapping
    @Operation(summary = "Updates a stock when given a changed stock from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Stock.class))})
    public ResponseEntity<String> putStock(@Valid @RequestBody Stock stock) {
        log.traceEntry("Enter postStock");

        if(repo.findById(stock.getId()).isPresent()) {
            saveStocks(stock);
            log.traceExit("Exit putStock", stock);
            return ResponseEntity.ok(StockServiceResponse.constructSuccessWithStock(stock));
        }
        else {
            log.traceExit("Exit putStock", stock);
            return ResponseEntity.badRequest().body(StockServiceResponse.constructErrorNoStockExists(stock.getId()));
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a stock when given its Id")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=Stock.class))})
    public ResponseEntity<String> deleteStock(long id) {
        log.traceEntry("Enter deleteStock", id);

        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            log.traceExit("Exit deleteStock");
            return ResponseEntity.ok(StockServiceResponse.constructDefaultSuccess());
        }
        else {
            log.traceExit("Exit deleteStock");
            return ResponseEntity.badRequest().body(StockServiceResponse.constructErrorNoStockExists(id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

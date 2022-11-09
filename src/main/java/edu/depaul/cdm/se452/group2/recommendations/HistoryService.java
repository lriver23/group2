package edu.depaul.cdm.se452.group2.recommendations;

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
@RequestMapping("/api/history")
@Tag(name = "History", description = "Allows manipulation of user history database items")
@Log4j2
public class HistoryService{
    @Autowired
    private HistoryRepository repo;
    
    @GetMapping
    @Operation(summary = "Returns a complete list of all items in history")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=History.class))})
    public List<History> getHistory() {
        log.traceEntry("Enter getHistory");
        var foundHistory = repo.findAll();
        log.traceExit("Exit getHistory", foundHistory);
        return foundHistory;
    }

    public long saveHistory(@NonNull History history) {
        log.traceEntry("Enter saveHistory");
        repo.save(history);
        log.traceExit("Exit saveHistory", history);
        return history.getId();
    }

    @PostMapping
    @Operation(summary = "Adds item to history and returns its name")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=History.class))})
    public ResponseEntity<String> postHistory(@Valid @RequestBody History history) {
        log.traceEntry("Enter postHistory");
        var savedHistoryId = saveHistory(history);
        log.traceExit("Exit postHistory", saveHistory(history));
        return ResponseEntity.ok("History list added from search, new id is " + savedHistoryId);
    }


    @PutMapping
    @Operation(summary = "Updates search history item given changed search item from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=History.class))})
    public ResponseEntity<String> putHistory(@Valid @RequestBody History history) {
        log.traceEntry("Enter putHistory");

        if(repo.findById(history.getId()).isPresent()) {
            saveHistory(history);
            log.traceExit("Exit postHistory", history);
            return ResponseEntity.ok("History updated");
        }
        else {
            log.traceExit("Exit putHistory", history);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Deletes a item from history when given its name")
    @ApiResponse(responseCode = "200", description = "valid response",
    content = {@Content(mediaType="application/json", schema=@Schema(implementation=History.class))})
    public void deleteHistory(long id) {
        log.traceEntry("Enter deleteHistory", id);
        repo.deleteById(id);
        log.traceExit("Exit deleteHistory");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiUtils.buildExceptionMap(ex);
    }
}

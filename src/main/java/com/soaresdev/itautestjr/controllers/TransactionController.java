package com.soaresdev.itautestjr.controllers;

import com.soaresdev.itautestjr.dtos.StatisticsDto;
import com.soaresdev.itautestjr.dtos.TransactionDto;
import com.soaresdev.itautestjr.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "Create transaction", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")
    })
    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Delete all transactions", method = "DELETE")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Get a summary of transactions that occurred in the last specified seconds", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content()),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity", content = @Content())
    })
    @GetMapping
    public ResponseEntity<StatisticsDto> calculateStatistics(@RequestParam(required = false, defaultValue = "60") Long seconds) {
        return ResponseEntity.ok(transactionService.calculateStatistics(seconds));
    }
}
package com.soaresdev.itautestjr.controllers;

import com.soaresdev.itautestjr.dtos.StatisticsDto;
import com.soaresdev.itautestjr.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@Tag(name = "Statistic")
public class StatisticController {
    private final TransactionService transactionService;

    public StatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "Get a summary of transactions that occurred in the last specified seconds", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content()),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity", content = @Content())
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDto> calculateStatistics(@RequestParam(required = false, defaultValue = "60") Long seconds) {
        return ResponseEntity.ok(transactionService.calculateStatistics(seconds));
    }
}
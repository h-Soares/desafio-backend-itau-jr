package com.soaresdev.itautestjr.services;

import com.soaresdev.itautestjr.dtos.StatisticsDto;
import com.soaresdev.itautestjr.dtos.TransactionDto;
import com.soaresdev.itautestjr.entities.Transaction;
import com.soaresdev.itautestjr.exceptions.DateTimeAfterNowException;
import com.soaresdev.itautestjr.exceptions.NegativeAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new LinkedList<>();
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        logger.info("Creating new transaction");
        logger.info("Original transaction: {}", transactionDto);

        OffsetDateTime transactionDtoDateHourUtc = transactionDto.getDataHour().withOffsetSameInstant(ZoneOffset.UTC);

        logger.info("dataHour to UTC: {}", transactionDtoDateHourUtc);

        if(transactionDtoDateHourUtc.isAfter(OffsetDateTime.now(ZoneOffset.UTC)))
            throw new DateTimeAfterNowException("Date and hour cannot be after now");
        if(transactionDto.getAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeAmountException("Amount cannot be negative");

        Transaction transaction = new Transaction(transactionDto.getAmount(), transactionDtoDateHourUtc);
        transactions.add(transaction);

        logger.info("Transaction successfully created: {}", transaction);

        return new TransactionDto(transaction);
    }

    public void deleteAllTransactions() {
        logger.info("Deleting all {} transactions", transactions.size());

        transactions.clear();

        logger.info("Transactions successfully deleted.");
    }

    public StatisticsDto calculateStatistics(Long seconds) {
        logger.info("Calculating statistics");
        long start = System.nanoTime();

        if(seconds < 0)
            throw new IllegalArgumentException("Seconds cannot be negative");

        DoubleSummaryStatistics statistics = transactions.stream().
                filter(t -> Duration.between(t.getDataHour(), OffsetDateTime.now()).toSeconds() <= seconds).
                collect(Collectors.summarizingDouble(t -> t.getAmount().doubleValue()));

        logger.info("Time to calculate statistics: {} seconds", (System.nanoTime() - start) / 1_000_000_000.0);
        logger.info("Statistics: {}. If count is zero will return filled with zero", statistics);

        if(statistics.getCount() == 0)
            return new StatisticsDto(0, 0.0, 0.0, 0.0, 0.0);

        return new StatisticsDto(statistics);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(new LinkedList<>(transactions));
    }
}
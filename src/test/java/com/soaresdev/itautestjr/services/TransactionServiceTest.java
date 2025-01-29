package com.soaresdev.itautestjr.services;

import com.soaresdev.itautestjr.dtos.StatisticsDto;
import com.soaresdev.itautestjr.dtos.TransactionDto;
import com.soaresdev.itautestjr.exceptions.DateTimeAfterNowException;
import com.soaresdev.itautestjr.exceptions.NegativeAmountException;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceTest {
    private final TransactionService transactionService;
    private static TransactionDto transaction;
    private static final Long DEFAULT_SECONDS = 60L;

    public TransactionServiceTest() {
        this.transactionService = new TransactionService();
    }

    @BeforeAll
    static void init() {
        transaction = new TransactionDto(BigDecimal.ONE, OffsetDateTime.now());
    }

    @BeforeEach
    void setUp() {
        transaction.setAmount(BigDecimal.ONE);
        transaction.setDataHour(OffsetDateTime.now());
    }

    @Test
    @Order(1)
    void shouldCreateTransaction() {
        TransactionDto result = transactionService.createTransaction(transaction);

        assertNotNull(result);
        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(transaction.getDataHour().withOffsetSameInstant(ZoneOffset.UTC), result.getDataHour());
        assertEquals(1, transactionService.getTransactions().size());
    }

    @Test
    void shouldThrowDateTimeAfterNowExceptionWhenDateHourIsAfterNowInCreateTransaction() {
        transaction.setDataHour(OffsetDateTime.now().plusHours(4));

        Throwable e = assertThrows(DateTimeAfterNowException.class,
                () -> transactionService.createTransaction(transaction));
        assertEquals("Date and hour cannot be after now", e.getMessage());
    }

    @Test
    void shouldThrowNegativeAmountExceptionWhenAmountIsNegativeInCreateTransaction() {
        transaction.setAmount(BigDecimal.valueOf(-1));

        Throwable e = assertThrows(NegativeAmountException.class,
                () -> transactionService.createTransaction(transaction));
        assertEquals("Amount cannot be negative", e.getMessage());
    }

    @Test
    @Order(2)
    void shouldDeleteAllTransactions() {
        transactionService.createTransaction(transaction);
        transactionService.deleteAllTransactions();

        assertEquals(0, transactionService.getTransactions().size());
    }

    @Test
    void shouldCalculateStatistics() {
        transactionService.createTransaction(transaction);
        transactionService.createTransaction(new TransactionDto(BigDecimal.TEN, OffsetDateTime.now()));

        StatisticsDto statisticsDto = transactionService.calculateStatistics(DEFAULT_SECONDS);

        assertNotNull(statisticsDto);
        assertEquals(2, statisticsDto.count());
        assertEquals(11, statisticsDto.sum());
        assertEquals(1, statisticsDto.min());
        assertEquals(5.5, statisticsDto.avg());
        assertEquals(10, statisticsDto.max());
    }

    @Test
    void shouldReturnFilledWithZeroWhenTimeNotMatchInCalculateStatistics() {
        transaction.setDataHour(OffsetDateTime.now().minusSeconds(70));
        transactionService.createTransaction(transaction);

        StatisticsDto statisticsDto = transactionService.calculateStatistics(DEFAULT_SECONDS);

        assertNotNull(statisticsDto);
        assertEquals(0, statisticsDto.count());
        assertEquals(0.0, statisticsDto.sum());
        assertEquals(0.0, statisticsDto.min());
        assertEquals(0.0, statisticsDto.avg());
        assertEquals(0.0, statisticsDto.max());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSecondsIsNegativeInCalculateStatistics() {
        Throwable e = assertThrows(IllegalArgumentException.class,
                () -> transactionService.calculateStatistics(-1L));
        assertEquals("Seconds cannot be negative", e.getMessage());
    }
}
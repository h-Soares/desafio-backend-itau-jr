package com.soaresdev.itautestjr.dtos;

import com.soaresdev.itautestjr.entities.Transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDto(BigDecimal amount, OffsetDateTime dataHour) {
    public TransactionDto(Transaction transaction) {
        this(transaction.getAmount(), transaction.getDataHour());
    }
}
package com.soaresdev.itautestjr.dtos;

import com.soaresdev.itautestjr.entities.Transaction;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransactionDto {
    private BigDecimal amount;
    private OffsetDateTime dataHour;

    public TransactionDto(BigDecimal amount, OffsetDateTime dataHour) {
        this.amount = amount;
        this.dataHour = dataHour;
    }

    public TransactionDto(Transaction transaction) {
        this(transaction.getAmount(), transaction.getDataHour());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OffsetDateTime getDataHour() {
        return dataHour;
    }

    public void setDataHour(OffsetDateTime dataHour) {
        this.dataHour = dataHour;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionDto{");
        sb.append("amount = ").append(amount);
        sb.append(", dataHour = ").append(dataHour);
        sb.append('}');
        return sb.toString();
    }
}
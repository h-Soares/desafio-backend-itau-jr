package com.soaresdev.itautestjr.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Transaction {
    private BigDecimal amount;
    private OffsetDateTime dataHour;

    public Transaction(BigDecimal amount, OffsetDateTime dataHour) {
        this.amount = amount;
        this.dataHour = dataHour;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) && Objects.equals(dataHour, that.dataHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, dataHour);
    }
}
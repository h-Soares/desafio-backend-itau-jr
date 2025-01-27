package com.soaresdev.itautestjr.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
}
package com.paaro.traderdemo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class BetDto {

    private long traderId;

    @NotNull(message = "Played amount must not be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Played amount cannot be less than 0.01 EUR.")
    @Digits(integer = 8, fraction = 2, message = "Played amount must be in N.NN form.")
    private BigDecimal playedAmount;

    @NotNull(message = "Odd must not be null.")
    @DecimalMin(value = "1.0", message = "Odd cannot be less than 1.00.")
    @Digits(integer = 8, fraction = 2, message = "Odd must be in N.NN form.")
    private BigDecimal odd;

    public BetDto() {
    }

    public BetDto(final long traderId, final BigDecimal playedAmount, final BigDecimal odd) {
        this.traderId = traderId;
        this.playedAmount = playedAmount;
        this.odd = odd;
    }

    public long getTraderId() {
        return traderId;
    }

    public BigDecimal getPlayedAmount() {
        return playedAmount;
    }

    public BigDecimal getOdd() {
        return odd;
    }
}

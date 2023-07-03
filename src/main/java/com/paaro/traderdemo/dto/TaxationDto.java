package com.paaro.traderdemo.dto;

import java.math.BigDecimal;

public class TaxationDto {

    private BigDecimal possibleReturnAmount;
    private BigDecimal possibleReturnAmountBefTax;
    private BigDecimal possibleReturnAmountAfterTax;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;

    public TaxationDto() {
    }

    public TaxationDto(final BigDecimal possibleReturnAmount, final BigDecimal possibleReturnAmountBefTax,
        final BigDecimal possibleReturnAmountAfterTax, final BigDecimal taxRate, final BigDecimal taxAmount) {

        this.possibleReturnAmount = possibleReturnAmount;
        this.possibleReturnAmountBefTax = possibleReturnAmountBefTax;
        this.possibleReturnAmountAfterTax = possibleReturnAmountAfterTax;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }

    public BigDecimal getPossibleReturnAmount() {
        return possibleReturnAmount;
    }

    public BigDecimal getPossibleReturnAmountBefTax() {
        return possibleReturnAmountBefTax;
    }

    public BigDecimal getPossibleReturnAmountAfterTax() {
        return possibleReturnAmountAfterTax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
}

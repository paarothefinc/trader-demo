package com.paaro.traderdemo.dto;

public enum TaxationType {
    /**
     * taxation is done per rate:<br><ul><li>rate: 10%<ul><li>7.5 EUR * 0.1 = 0.75 EUR => possible return amount is 7.5
     * EUR - 0.75 EUR = 6.75 EUR</li></ul></li></ul>
     */
    GENERAL_RATE,
    /**
     * taxation is done per amount:<br><ul><li>amount: 2 EUR<ul><li>7.5 EUR - 2 EUR = 5.5 EUR => possible return amount
     * is 5.5 EUR</li></ul></li></ul>
     */
    GENERAL_AMOUNT,
    /**
     * taxation is done per rate:<br><ul><li>winnings amount: 7.5 EUR - 5 EUR = 2.5 EUR</li><li>rate: 10%<ul><li>2.5 EUR
     * * 0.1 = 0.25 EUR => possible return amount is 7.5 EUR - 0.25 EUR = 7.25 EUR</li></ul></li></ul>
     */
    WINNINGS_RATE,
    /**
     * taxation is done per amount:<br><ul><li>winnings amount: 7.5 EUR - 5 EUR = 2.5 EUR</li><li>amount: 1
     * EUR<ul><li>2.5 EUR - 1 EUR = 1.5 EUR => possible return amount is 1.5 EUR</li></ul></li></ul>
     */
    WINNINGS_AMOUNT;
}

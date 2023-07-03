package com.paaro.traderdemo.exception;

public final class TraderNotFoundException extends Exception {
    public TraderNotFoundException(final Long id) {
        super(String.format("Trader with id=%d not found!", id));
    }
}

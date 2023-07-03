package com.paaro;

import com.paaro.traderdemo.dto.BetDto;
import com.paaro.traderdemo.dto.TaxationDto;
import com.paaro.traderdemo.exception.TraderNotFoundException;
import com.paaro.traderdemo.service.TaxationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestTransaction
public class TaxationServiceTest {

    @Inject
    TaxationService taxationService;

    @Test
    public void testTaxationGeneralRate() throws TraderNotFoundException {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(2000, 2), BigDecimal.valueOf(300, 2));
        final TaxationDto taxation = taxationService.getTaxation(betDto);

        Assertions.assertEquals(BigDecimal.valueOf(5400L, 2), taxation.getPossibleReturnAmount());
        Assertions.assertEquals(BigDecimal.valueOf(6000L, 2), taxation.getPossibleReturnAmountBefTax());
        Assertions.assertEquals(BigDecimal.valueOf(600L, 2), taxation.getPossibleReturnAmountAfterTax());
        Assertions.assertEquals(BigDecimal.valueOf(10L, 2), taxation.getTaxRate());
        Assertions.assertEquals(BigDecimal.valueOf(600L, 2), taxation.getTaxAmount());
    }

    @Test
    public void testTraderNotFound() {
        final BetDto betDto = new BetDto(13L, BigDecimal.valueOf(1000, 2), BigDecimal.valueOf(150, 2));

        TraderNotFoundException e = Assertions.assertThrows(TraderNotFoundException.class,
            () -> taxationService.getTaxation(betDto));

        Assertions.assertEquals("Trader with id=13 not found!", e.getMessage());
    }
}

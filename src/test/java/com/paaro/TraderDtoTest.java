package com.paaro;

import com.paaro.traderdemo.dto.BetDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class TraderDtoTest {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidBet() {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(15500, 2), BigDecimal.valueOf(115, 2));
        final Set<ConstraintViolation<BetDto>> violations = validator.validate(betDto);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testNegativeAmount() {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(-1), BigDecimal.valueOf(115, 2));
        final Set<ConstraintViolation<BetDto>> violations = validator.validate(betDto);

        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Played amount cannot be less than 0.01 EUR.",
            violations.iterator().next().getMessage());
    }

    @Test
    public void testMissingAmount() {
        final BetDto betDto = new BetDto(1L, null, BigDecimal.valueOf(115, 2));
        final Set<ConstraintViolation<BetDto>> violations = validator.validate(betDto);

        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Played amount must not be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testOddLessThanOne() {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(5555, 2), BigDecimal.valueOf(99, 2));
        final Set<ConstraintViolation<BetDto>> violations = validator.validate(betDto);

        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Odd cannot be less than 1.00.", violations.iterator().next().getMessage());
    }

    @Test
    public void testMissingOdd() {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(5555, 2), null);
        final Set<ConstraintViolation<BetDto>> violations = validator.validate(betDto);

        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Odd must not be null.", violations.iterator().next().getMessage());
    }
}

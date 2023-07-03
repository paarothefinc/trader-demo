package com.paaro.traderdemo.service;

import com.paaro.traderdemo.dto.BetDto;
import com.paaro.traderdemo.dto.TaxationDto;
import com.paaro.traderdemo.dto.TaxationType;
import com.paaro.traderdemo.exception.TraderNotFoundException;
import com.paaro.traderdemo.persistence.model.Trader;
import com.paaro.traderdemo.persistence.repository.TraderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Dependent
@Transactional
public final class TaxationService {

    @Inject
    TraderRepository traderRepository;

    public TaxationDto getTaxation(final BetDto betDto) throws TraderNotFoundException {
        final Optional<Trader> trader = traderRepository.findByIdOptional(betDto.getTraderId());

        return trader.map(
                x -> calculateTax(x.getTaxationType(), x.getValue(), betDto.getPlayedAmount(), betDto.getOdd()))
            .orElseThrow(() -> new TraderNotFoundException(betDto.getTraderId()));
    }

    private TaxationDto calculateTax(final TaxationType taxationType, final BigDecimal value,
        final BigDecimal playedAmount, final BigDecimal odd) {

        final BigDecimal possibleReturnAmount;
        final BigDecimal possibleReturnAmountBefTax = playedAmount.multiply(odd).setScale(2, RoundingMode.FLOOR);
        final BigDecimal possibleReturnAmountAfterTax;
        BigDecimal taxRate = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;

        switch (taxationType) {
            case GENERAL_RATE -> {
                taxRate = value;
                taxAmount = possibleReturnAmountBefTax.multiply(value).setScale(2, RoundingMode.FLOOR);
            }
            case GENERAL_AMOUNT -> {
                taxAmount = value;
                taxRate = taxAmount.divide(possibleReturnAmountBefTax, 5, RoundingMode.FLOOR);
            }
            case WINNINGS_RATE -> {
                taxRate = value;
                taxAmount = taxRate.multiply(possibleReturnAmountBefTax.subtract(playedAmount)).setScale(2,
                    RoundingMode.FLOOR);
            }
            case WINNINGS_AMOUNT -> {
                taxAmount = value;
                taxRate = taxAmount.divide(possibleReturnAmountBefTax, 5, RoundingMode.FLOOR);
            }
        }

        possibleReturnAmount = possibleReturnAmountBefTax.subtract(taxAmount).setScale(2, RoundingMode.FLOOR);
        possibleReturnAmountAfterTax = possibleReturnAmountBefTax.subtract(possibleReturnAmount).setScale(2,
            RoundingMode.FLOOR);

        return new TaxationDto(possibleReturnAmount, possibleReturnAmountBefTax, possibleReturnAmountAfterTax, taxRate,
            taxAmount);
    }
}

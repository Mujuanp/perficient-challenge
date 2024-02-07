package com.perficient.challenge.payment;

import java.math.BigDecimal;

public abstract class FeeDecorator implements FeeStrategy {
    private final FeeStrategy feeStrategy;

    public FeeDecorator(final FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    @Override
    public BigDecimal calculatePriceWithFee(final BigDecimal price) {
        return feeStrategy.calculatePriceWithFee(price).add(calculateFee(price));
    }

    protected abstract BigDecimal calculateFee(final BigDecimal price);
}
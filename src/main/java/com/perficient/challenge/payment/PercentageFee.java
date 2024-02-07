package com.perficient.challenge.payment;

import java.math.BigDecimal;

public  class PercentageFee extends FeeDecorator {
    private final BigDecimal percentage;

    public PercentageFee(final FeeStrategy feeStrategy, final BigDecimal percentage) {
        super(feeStrategy);
        this.percentage = percentage;
    }

    @Override
    protected BigDecimal calculateFee(final BigDecimal price) {
        return price.multiply(percentage);
    }
}

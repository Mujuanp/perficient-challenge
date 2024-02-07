package com.perficient.challenge.payment;

import java.math.BigDecimal;

public class PlusValueFee extends FeeDecorator {
    private BigDecimal value;

    public PlusValueFee(final FeeStrategy feeStrategy, final BigDecimal value) {
        super(feeStrategy);
        this.value = value;
    }

    @Override
    public BigDecimal calculateFee(final BigDecimal price) {
        return value;
    }
}

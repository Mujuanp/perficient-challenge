package com.perficient.challenge.payment;

import java.math.BigDecimal;

public class NoFeeStrategy implements FeeStrategy {
    @Override
    public BigDecimal calculatePriceWithFee(final BigDecimal price) {
        return price;
    }
}

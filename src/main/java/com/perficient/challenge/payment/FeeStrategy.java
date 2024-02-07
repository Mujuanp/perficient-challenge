package com.perficient.challenge.payment;

import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal calculatePriceWithFee(BigDecimal price);

}

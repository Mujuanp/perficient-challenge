package com.perficient.challenge.payment;

import java.math.BigDecimal;

public class MastercardFee extends PercentageFee {
    private static final BigDecimal FEE = new BigDecimal("0.04");

    public MastercardFee(FeeStrategy feeStrategy) {
        super(feeStrategy, FEE);
    }
}

package com.perficient.challenge.controller;

import com.perficient.challenge.service.CartService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Controller("cart")
@Introspected
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @Get("/checkout/{payment_method}")
    public HttpResponse<BigDecimal> getTotalWithFees(@PathVariable("payment_method") String paymentMethod) {

        return HttpResponse.ok(cartService.calculateTotalWithFees(paymentMethod));
    }
}

package com.perficient.challenge.service;

import com.perficient.challenge.model.Cart;
import com.perficient.challenge.model.CartHasProduct;
import com.perficient.challenge.model.CartProductKey;
import com.perficient.challenge.model.Fee;
import com.perficient.challenge.model.Product;
import com.perficient.challenge.payment.FeeStrategy;
import com.perficient.challenge.payment.NoFeeStrategy;
import com.perficient.challenge.payment.PercentageFee;
import com.perficient.challenge.payment.PlusValueFee;
import com.perficient.challenge.repository.CartRepository;
import com.perficient.challenge.repository.FeeRepository;
import com.perficient.challenge.repository.ProductRepository;
import io.micronaut.core.annotation.Introspected;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Singleton
@Introspected
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final FeeRepository feeRepository;
    private final ProductRepository productRepository;

    private final Map<Product, Integer> itemsQuantity = new HashMap<>();
    private final Map<String, FeeStrategy> feeStrategy = new HashMap<>();

    @PostConstruct
    public void init() {
        fillDatabase();

        cartRepository.findById(3L).ifPresent(this::fillItemsQuantityMap);
        feeRepository.findAll().forEach(fee -> feeStrategy.put(
                        fee.getName(),
                        new PlusValueFee(
                                new PercentageFee(
                                        new NoFeeStrategy(),
                                        BigDecimal.valueOf(fee.getPercentageFee())),
                                BigDecimal.valueOf(fee.getPlusValueFee())
                        )
                )
        );
    }

    private void fillDatabase() {// Ignore this code. It is only to fill database. There are better ways to do it, it is only for this test.

        Fee fee = new Fee(null, "Visa", 0.02, 800.0);
        Fee fee2 = new Fee(null, "MasterCard", 0.04, 800.0);

        feeRepository.save(fee);
        feeRepository.save(fee2);

        Cart cart = new Cart(null, new LinkedList<>());

        cartRepository.save(cart);


        Product p = new Product(null, "Banana", new BigDecimal("2000"), new LinkedList<>());
        Product p2 = new Product(null, "Orange", new BigDecimal("1000"), new LinkedList<>());
        Product p3 = new Product(null, "Strawberry", new BigDecimal("2000"), new LinkedList<>());

        productRepository.save(p);
        productRepository.save(p2);
        productRepository.save(p3);


        List<CartHasProduct> products = new LinkedList<>();
        CartHasProduct chp1 = new CartHasProduct(new CartProductKey(cart.getId(), p.getId()), cart, p, 2);
        CartHasProduct chp2 = new CartHasProduct(new CartProductKey(cart.getId(), p2.getId()), cart, p2, 3);
        CartHasProduct chp3 = new CartHasProduct(new CartProductKey(cart.getId(), p3.getId()), cart, p3, 3);

        products.add(chp1);
        products.add(chp2);
        products.add(chp3);
        cart.setItems(products);
        cartRepository.update(cart);
    }

    private void fillItemsQuantityMap(final Cart cart) {
        cart.getItems().forEach(item -> addItem(item.getProduct(), item.getQuantity()));
    }

    public void addItem(@Valid final Product product, @Min(1) final int quantity) {
        final Optional<Integer> possibleQuantity = Optional.ofNullable(itemsQuantity.get(product));
        itemsQuantity.put(product, possibleQuantity.orElse(0) + quantity);
    }

    public BigDecimal calculateTotalWithFees(final String paymentMethod) {
        Optional.ofNullable(feeStrategy.get(paymentMethod))
                .ifPresentOrElse(strategy ->
                                strategy.calculatePriceWithFee(calculateTotal()),
                        () -> {
                            throw new IllegalArgumentException("Payment method is not supported");
                        });
        return feeStrategy.get(paymentMethod).calculatePriceWithFee(calculateTotal());
    }

    private BigDecimal calculateTotal() {
        return itemsQuantity.entrySet().parallelStream()
                .map(entry ->
                        entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()))
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

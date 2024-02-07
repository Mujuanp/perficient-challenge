package com.perficient.challenge.model;

import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.validation.Validated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @Min(0)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<CartHasProduct> carts;
}

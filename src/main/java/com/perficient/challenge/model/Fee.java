package com.perficient.challenge.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.validation.Validated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

@Introspected
@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Entity
@Table(name = "fees")
public class Fee {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Column(name = "percentage_fee")
    private Double percentageFee;

    @NotNull
    @Column(name = "plus_value_fee")
    private Double plusValueFee;
}

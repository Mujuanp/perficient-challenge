package com.perficient.challenge.repository;

import com.perficient.challenge.model.Cart;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.Valid;

@Repository
public interface CartRepository extends CrudRepository<@Valid Cart, Long> {
}

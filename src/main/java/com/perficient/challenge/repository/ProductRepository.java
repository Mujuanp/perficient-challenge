package com.perficient.challenge.repository;

import com.perficient.challenge.model.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.Valid;

@Repository
public interface ProductRepository extends CrudRepository<@Valid Product, Long> {
}

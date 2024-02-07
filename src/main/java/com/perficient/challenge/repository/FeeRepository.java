package com.perficient.challenge.repository;

import com.perficient.challenge.model.Fee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.Valid;

@Repository
public interface FeeRepository extends CrudRepository<@Valid Fee, Long> {
}

package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Parameter;

public interface ParameterRepository
    extends JpaRepository<Parameter, Long>, QuerydslPredicateExecutor<Parameter> {
  boolean existsByName(String name);
}

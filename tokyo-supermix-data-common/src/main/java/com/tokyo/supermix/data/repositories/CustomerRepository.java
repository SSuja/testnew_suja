package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.Customer;

public interface CustomerRepository
    extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {
  boolean existsByEmail(String mail);

  boolean existsByName(String name);
}

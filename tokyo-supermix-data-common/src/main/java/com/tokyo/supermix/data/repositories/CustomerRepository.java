package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  boolean existsByEmail(String mail);
}

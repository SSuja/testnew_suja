package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>,
    QuerydslPredicateExecutor<Customer>, PagingAndSortingRepository<Customer, Long> {
  boolean existsByEmail(String mail);

  boolean existsByName(String name);

  List<Customer> findByPlantCode(String plantCode);

  List<Customer> findByPlantCodeIn(List<String> plantCodes);

  Customer findByName(String name);

  Page<Customer> findAll(Pageable pageable);

  List<Customer> findAllByPlantCode(String plantCode, Pageable pageable);

  Long countByPlantCode(String plantCode);
}

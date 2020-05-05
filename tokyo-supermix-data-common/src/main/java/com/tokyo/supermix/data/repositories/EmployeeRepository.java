package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.Employee;

@Repository
public interface EmployeeRepository
    extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {
  /* Check Existing email */
  boolean existsByEmail(String mail);
}

package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Designation;

public interface DesignationRepository
    extends JpaRepository<Designation, Long>, QuerydslPredicateExecutor<Designation> {
  boolean existsByName(String name);

  Designation findByName(String name);
}

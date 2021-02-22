package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.SubBusinessUnit;

public interface SubBusinessUnitRepository
    extends JpaRepository<SubBusinessUnit, Long>, QuerydslPredicateExecutor<SubBusinessUnit> {

  boolean existsByName(String name);
}

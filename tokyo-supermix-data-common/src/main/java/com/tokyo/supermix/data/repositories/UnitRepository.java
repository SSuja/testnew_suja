package com.tokyo.supermix.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long>, QuerydslPredicateExecutor<Unit> {
  boolean existsByUnit(String unit);

  Unit findByUnit(String unit);

  Page<Unit> findAllByOrderByUpdatedAtDesc(Pageable pageable);
}

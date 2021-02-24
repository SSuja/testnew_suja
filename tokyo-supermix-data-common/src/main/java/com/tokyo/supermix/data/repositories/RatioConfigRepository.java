package com.tokyo.supermix.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.RatioConfig;

public interface RatioConfigRepository extends JpaRepository<RatioConfig, Long>,
    QuerydslPredicateExecutor<RatioConfig>, PagingAndSortingRepository<RatioConfig, Long> {
  boolean existsByName(String name);

  Page<RatioConfig> findAllByOrderByIdDesc(Pageable pageable);
}

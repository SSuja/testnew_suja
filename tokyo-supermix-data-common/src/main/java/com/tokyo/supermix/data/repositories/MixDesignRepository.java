package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MixDesign;

public interface MixDesignRepository
    extends JpaRepository<MixDesign, String>, QuerydslPredicateExecutor<MixDesign> {
  boolean existsByCode(String code);
}

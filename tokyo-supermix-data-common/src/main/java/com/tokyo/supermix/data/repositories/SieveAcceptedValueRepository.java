package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;

public interface SieveAcceptedValueRepository
    extends JpaRepository<SieveAcceptedValue, Long>, QuerydslPredicateExecutor<SieveAcceptedValue> {
  boolean existsBySieveSizeId(Long sieveSizeId);

  SieveAcceptedValue findBySieveSizeMaterialSubCategoryId(Long materialSubCategoryId);
}

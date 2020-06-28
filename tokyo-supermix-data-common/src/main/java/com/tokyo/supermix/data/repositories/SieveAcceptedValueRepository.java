package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;

public interface SieveAcceptedValueRepository
    extends JpaRepository<SieveAcceptedValue, Long>, QuerydslPredicateExecutor<SieveAcceptedValue> {
  boolean existsBySieveSizeId(Long sieveSizeId);

  SieveAcceptedValue findBySieveSizeId(Long sieveSizeId);

  List<SieveAcceptedValue> findBySieveSizeMaterialSubCategoryId(Long materialSubCategoryId);
}

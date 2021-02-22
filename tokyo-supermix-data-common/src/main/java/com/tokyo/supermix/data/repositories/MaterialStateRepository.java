package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MaterialState;

public interface MaterialStateRepository extends JpaRepository<MaterialState, Long> , QuerydslPredicateExecutor<MaterialState>{
  boolean existsByMaterialState(String materialState);

  public MaterialState findByMaterialState(String materialState);
}

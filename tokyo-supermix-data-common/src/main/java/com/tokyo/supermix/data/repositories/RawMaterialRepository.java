package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.RawMaterial;

public interface RawMaterialRepository
    extends JpaRepository<RawMaterial, Long>, QuerydslPredicateExecutor<RawMaterial> {
  boolean existsByName(String name);

  List<RawMaterial> findByMaterialSubCategoryId(Long materialSubCategoryId);

  RawMaterial findByName(String name);

  List<RawMaterial> findByActiveTrue();
}

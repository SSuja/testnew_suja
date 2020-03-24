package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinenessModulus;

public interface FinenessModulusRepository extends JpaRepository<FinenessModulus, Long> {
  List<FinenessModulus> findByMaterialSubCategoryId(Long materialSubCategoryId);

  boolean existsByMaterialSubCategoryId(Long materialSubCategoryId);
}

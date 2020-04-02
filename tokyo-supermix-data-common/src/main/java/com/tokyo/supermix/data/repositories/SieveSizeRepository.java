package com.tokyo.supermix.data.repositories;


import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SieveSize;

public interface SieveSizeRepository extends JpaRepository<SieveSize, Long> {
  List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId);
  List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId, Sort sort);

  boolean existsBySizeAndMaterialSubCategoryId(Double size, Long materialSubCategoryId);
  SieveSize findBySizeAndMaterialSubCategoryId(Double size, Long materialSubCategoryId);
}

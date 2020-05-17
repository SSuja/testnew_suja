package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.QualityParameter;

@Repository
public interface QualityParameterRepository extends JpaRepository<QualityParameter, Long> {
  boolean existsByNameAndMaterialSubCategoryId(String name, Long materialSubCategotyId);

  List<QualityParameter> findByMaterialSubCategoryId(Long materialSubCategotyId);

  boolean existsByMaterialSubCategoryId(Long materialSubCategotyId);
}

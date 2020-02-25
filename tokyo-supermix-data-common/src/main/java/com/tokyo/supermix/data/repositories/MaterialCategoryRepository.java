package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.MaterialCategory;

@Repository
public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long> {
  /* Check Existing name */
  boolean existsByName(String name);
}

package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SupplierCategory;

public interface SupplierCategoryRepository extends JpaRepository<SupplierCategory, Long> {
  boolean existsByCategory(String category);

  SupplierCategory findByCategory(String category);
}

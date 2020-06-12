package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierRepository
    extends JpaRepository<Supplier, Long>, QuerydslPredicateExecutor<Supplier> {
  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  List<Supplier> findBySupplierCategoriesId(Long suppilerCategoryId);

  List<Supplier> findByPlantCode(String plantCode);
}

package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>,
    QuerydslPredicateExecutor<Supplier>, PagingAndSortingRepository<Supplier, Long> {
  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  List<Supplier> findBySupplierCategoriesIdAndPlantCode(Long suppilerCategoryId, String plantCode);

  List<Supplier> findByPlantCode(String plantCode);

  List<Supplier> findByPlantCodeIn(List<String> plantCodes);

  List<Supplier> findBySupplierCategoriesId(Long suppilerCategoryId);

  // List<Supplier> findByPlantCodeAndSupplierCategoriesId(String plantCode, Long
  // supplierCategoryId);

  boolean existsByPlantCodeAndSupplierCategoriesId(String plantCode, Long supplierCategoryId);

  Supplier findByEmail(String email);

  List<Supplier> findAllByPlantCodeIn(List<String> plantCodes, Pageable pageable);

  List<Supplier> findAllByPlantCode(String plantCode, Pageable pageable);

  Long countByPlantCode(String plantCode);

  List<Supplier> findByPlantCodeAndSupplierCategoriesIdAndNameContaining(String plantCode,
      Long supplierCategoryId, String supplierName);

  List<Supplier> findByNameContaining(String name);

  List<Supplier> findByPlantCodeAndNameContaining(String plantCode, String name);
  
  List<Supplier> findBySentMail(boolean sentMail);
}

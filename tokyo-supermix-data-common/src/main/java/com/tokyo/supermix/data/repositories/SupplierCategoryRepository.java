package com.tokyo.supermix.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.SupplierCategory;

public interface SupplierCategoryRepository
    extends JpaRepository<SupplierCategory, Long>, QuerydslPredicateExecutor<SupplierCategory>,
    PagingAndSortingRepository<SupplierCategory, Long> {

  boolean existsByCategory(String category);

  SupplierCategory findByCategory(String category);

  Page<SupplierCategory> findAllByOrderByIdDesc(Pageable pegeable);
}

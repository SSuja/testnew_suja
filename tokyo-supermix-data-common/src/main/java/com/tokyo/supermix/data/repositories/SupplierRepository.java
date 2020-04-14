package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  List<Supplier> findBySupplierCategoryId(Long supplierCategoryId);
}

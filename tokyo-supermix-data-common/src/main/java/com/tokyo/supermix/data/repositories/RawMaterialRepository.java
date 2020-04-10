package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.RawMaterial;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
  boolean existsByName(String name);
  List<RawMaterial> findByMaterialSubCategoryId(Long materialSubCategoryId);
}

package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.enums.MainType;

@Repository
public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long> {
  boolean existsByName(String name);

  MaterialCategory findByName(String name);

  List<MaterialCategory> findByMainType(MainType mainType);

  boolean existsByMainType(MainType mainType);

  boolean existsByNameAndMainType(String name, MainType mainType);
}

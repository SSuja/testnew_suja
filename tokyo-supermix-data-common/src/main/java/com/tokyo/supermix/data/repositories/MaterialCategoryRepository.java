package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.MaterialCategory;

@Repository
public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long> {
  boolean existsByName(String name);

  MaterialCategory findByName(String name);
  List<MaterialCategory> findAllByOrderByIdDesc();
}

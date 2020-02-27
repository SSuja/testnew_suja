package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;

public interface MaterialSubCategoryRepository extends JpaRepository<MaterialSubCategory, Long> {
	boolean existsByName(String name);
	List<MaterialSubCategory> findByMaterialCategory(MaterialCategory materialCategory);
}

package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, String> {
  boolean existsByName(String name);
}

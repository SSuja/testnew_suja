package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MaterialState;

public interface MaterialStateRepository extends JpaRepository<MaterialState, Long> {
  boolean existsByMaterialState(String materialState);
}

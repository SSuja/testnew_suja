package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MaterialTestTrial;

public interface MaterialTestTrialRepository extends JpaRepository<MaterialTestTrial, String> {
  boolean existsByMaterialTestCode(String materialTestCode);

  List<MaterialTestTrial> findByMaterialTestCode(String materialTestCode);
}

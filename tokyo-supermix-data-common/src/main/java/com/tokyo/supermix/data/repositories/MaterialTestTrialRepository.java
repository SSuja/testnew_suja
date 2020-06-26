package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;

public interface MaterialTestTrialRepository extends JpaRepository<MaterialTestTrial, String> {
  boolean existsByMaterialTestCode(String materialTestCode);

  List<MaterialTestTrial> findByMaterialTestCode(String materialTestCode);

  boolean existsByCode(String code);

  MaterialTestTrial findByCode(String code);

  List<MaterialTestTrial> findByMaterialTestIncomingSamplePlantCode(String plantCode);
  List<MaterialTestTrial> findByCodeContaining(String code);
}

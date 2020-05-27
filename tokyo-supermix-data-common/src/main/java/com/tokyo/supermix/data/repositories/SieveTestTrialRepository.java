package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SieveTestTrial;

public interface SieveTestTrialRepository extends JpaRepository<SieveTestTrial, Long> {
  List<SieveTestTrial> findBySieveTestCode(String sieveTestCode);

  List<SieveTestTrial> findBySieveTestPlantCode(String plantCode);
}

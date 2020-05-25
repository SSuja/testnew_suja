package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ParameterResult;

public interface ParameterResultRepository extends JpaRepository<ParameterResult, Long> {
  List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode);

  List<ParameterResult> findByMaterialTestTrialMaterialTestIncomingSamplePlantCode(
      String plantCode);
}

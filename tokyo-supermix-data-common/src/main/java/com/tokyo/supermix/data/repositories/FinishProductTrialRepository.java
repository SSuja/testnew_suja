package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.FinishProductTrial;
@Repository
public interface FinishProductTrialRepository extends JpaRepository<FinishProductTrial, String> {
  boolean existsByCode(String code);

  FinishProductTrial findFinishProductTrialByCode(String code);

  List<FinishProductTrial> findByFinishProductTestCode(String finishProductTestCode);

  List<FinishProductTrial> findByCodeContaining(String code);

  boolean existsByFinishProductTestCode(String finishProductTestCode);

  List<FinishProductTrial> findByFinishProductTestFinishProductSampleMixDesignPlantCodeIn(
      List<String> plantCodes);
  
  List<FinishProductTrial> findByFinishProductTestFinishProductSampleMixDesignPlantCode(
      String plantCode);
}

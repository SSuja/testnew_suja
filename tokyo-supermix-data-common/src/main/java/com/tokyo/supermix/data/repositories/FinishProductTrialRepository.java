package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductTrial;

public interface FinishProductTrialRepository extends JpaRepository<FinishProductTrial, String> {
  boolean existsByCode(String code);

  FinishProductTrial findFinishProductTrialByCode(String code);

  List<FinishProductTrial> findByFinishProductTestCode(String finsishproductTestCode);

  List<FinishProductTrial> findByCodeContaining(String code);
}

package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductTrial;

public interface FinishProductTrialRepository extends JpaRepository<FinishProductTrial, Long> {
  public List<FinishProductTrial> findByTestParameterId(Long testParameterId);

  public List<FinishProductTrial> findByFinishProductTestId(Long finishProductTestId);
}

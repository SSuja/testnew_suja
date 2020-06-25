package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductTrial;

<<<<<<< HEAD
public interface FinishProductTrialRepository extends JpaRepository<FinishProductTrial, Long> {
  public List<FinishProductTrial> findByTestParameterId(Long testParameterId);

  public List<FinishProductTrial> findByFinishProductTestId(Long finishProductTestId);
=======
public interface FinishProductTrialRepository extends JpaRepository<FinishProductTrial, String> {


  boolean existsByCode(String code);

  FinishProductTrial findFinishProductTrialByCode(String code);

  List<FinishProductTrial> findByCodeContaining(String code);
>>>>>>> d33e579e187c7b60c2a90d9ba4dbdb7dde209f62
}

package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.StrengthCubeTestFinding;

public interface StrengthCubeTestFindingService {
  public List<StrengthCubeTestFinding> getAllCubeTestFindings();

  boolean isCubeTestFindingExist(Long id);

  public StrengthCubeTestFinding getCubeTestFindingById(Long id);

  public void saveCubeTestFinding(StrengthCubeTestFinding cubeTestFinding);

  public void deleteCubeTestFinding(Long id);

  public boolean checkAge(Long age);

  public List<StrengthCubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  public List<StrengthCubeTestFinding> findByConcreteTestId(Long concreteTestId);

  public boolean existsByFinishProductSampleId(Long finishProductSampleId);

  public boolean existsByConcreteTestId(Long concreteTestId);
}

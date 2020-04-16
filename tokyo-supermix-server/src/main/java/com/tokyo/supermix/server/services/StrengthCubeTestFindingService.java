package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.StrengthCubeTestFinding;

public interface StrengthCubeTestFindingService {
  public List<StrengthCubeTestFinding> getAllStrengthCubeTestFindings();

  boolean isStrengthCubeTestFindingExist(Long id);

  public StrengthCubeTestFinding getStrengthCubeTestFindingById(Long id);

  public void saveStrengthCubeTestFindingCubeTestFinding(
      StrengthCubeTestFinding strengthCubeTestFinding);

  public void deleteStrengthCubeTestFinding(Long id);

  public boolean checkAge(Long age);

  public List<StrengthCubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  public boolean existsByFinishProductSampleId(Long finishProductSampleId);
}

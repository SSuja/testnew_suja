package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.CubeTestFinding;

public interface CubeTestFindingService {
  public List<CubeTestFinding> getAllCubeTestFindings();

  boolean isCubeTestFindingExist(Long id);

  public CubeTestFinding getCubeTestFindingById(Long id);

  public void saveCubeTestFinding(CubeTestFinding cubeTestFinding);

  public void deleteCubeTestFinding(Long id);

  public boolean checkAge(Long age);

  public List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  public List<CubeTestFinding> findByConcreteTestElementId(Long concreteTestElementId);
}

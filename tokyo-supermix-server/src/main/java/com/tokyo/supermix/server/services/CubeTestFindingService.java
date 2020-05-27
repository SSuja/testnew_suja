package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.CubeTestFinding;

public interface CubeTestFindingService {
  public List<CubeTestFinding> getAllCubeTestFindings();

  boolean isCubeTestFindingExist(Long id);

  public CubeTestFinding getCubeTestFindingById(Long id);

  public void saveCubeTestFinding(CubeTestFinding cubeTestFinding);

  public void updateCubTestFinding(List<CubeTestFinding> cubeTestFindinglist);

  public void deleteCubeTestFinding(Long id);

  public boolean checkAge(Long age);

  public List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  public boolean existsByFinishProductSampleId(Long finishProductSampleId);

  public Page<CubeTestFinding> searchCubeTestFinding(Predicate predicate, int size, int page);

  public void saveCubeTestFindingConcretTestResultStrengthAverage(Long finishProductSampleId,
      Long concreteAge);

  public void saveCubeTestFindingConcretTestResultStrengthGradeRatio(Long finishProductSampleId,
      Long concreteAge);

  public boolean checkConcreteStatus(Long finishProductSampleId);
}

package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.enums.Status;

public interface ConcreteTestResultService {
  public void saveConcreteTestResult(ConcreteTestResult concreteTestResult);

  public List<ConcreteTestResult> getAllConcreteTestResults();

  public ConcreteTestResult getConcreteTestResultById(Long id);

  public List<ConcreteTestResult> findByConcreteTestTypeId(Long concreteTestTypeId);

  public void deleteConcreteTestResult(Long id);

  public boolean isConcreteTestResultExists(Long id);

  public void saveConcreteSlumpTestSlumpResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteSlumpTestWaterCementRatioResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteTestWaterBinderRatioResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteSlumpTestSlumpGradeRatioResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteMoistureResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteStrengthTestAverageStrengthResult(ConcreteTestResult concreteTestResult);

  public void saveConcreteStrengthTestStrengthGradeRatioResult(
      ConcreteTestResult concreteTestResult);

  public ConcreteTestResult findByConcreteTestId(Long concreteTestId);

  public List<ConcreteTestResult> findByFinishProductSampleId(Long finishProductSampleId);

  public void saveConcreteTestStrengthStatus(Long finishProductSampleId);

  public void saveConcreteTestSlumpStatus(Long finishProductSampleId);

  public void saveConcreteTestMoistureStatus(Long finishProductSampleId);

  public Page<ConcreteTestResult> searchConcreteTestResult(Long finishProductSampleId,
      Long ConcreteTestId, Status status, Double result, Double resultMin, Double resultMax,
      BooleanBuilder booleanBuilder, int page, int size);
}

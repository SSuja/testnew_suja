package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.ConcreteTestResult;

public interface ConcreteTestResultRepository
    extends JpaRepository<ConcreteTestResult, Long>, QuerydslPredicateExecutor<ConcreteTestResult> {
  List<ConcreteTestResult> findByFinishProductSampleId(Long finishProductSampleId);

  ConcreteTestResult findByConcreteTestId(Long concreteTestId);

  List<ConcreteTestResult> findByConcreteTestConcreteTestTypeId(Long concreteTestTypeId);

  List<ConcreteTestResult> findByConcreteTestConcreteTestTypeIdAndFinishProductSampleId(
      Long concreteTestTypeId, Long finishProductSampleId);

  List<ConcreteTestResult> findByConcreteTestConcreteTestTypeTypeAndConcreteTestName(
      String concreteTestType, String concreteTestName);

  ConcreteTestResult findByFinishProductSampleIdAndConcreteTestName(
      Long finishProductSampleId, String concreteTestName);
}

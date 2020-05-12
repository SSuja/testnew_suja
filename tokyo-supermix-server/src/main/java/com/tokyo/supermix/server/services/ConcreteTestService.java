package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.enums.Status;

public interface ConcreteTestService {
  public ConcreteTest saveConcreteTest(ConcreteTest concreteTest);

  public List<ConcreteTest> getAllConcreteTests();

  public ConcreteTest getConcreteTestById(Long id);

  public void deleteConcreteTest(Long id);

  public boolean isConcreteTestExists(Long id);

  public Page<ConcreteTest> searchConcreteTest(Long finishProductSampleId, Status status,
      Double slump, Double slumpMin, Double slumpMax, Double slumpGradeRatio,
      Double slumpGradeRatioMin, Double slumpGradeRatioMax, BooleanBuilder booleanBuilder, int page,
      int size);
}

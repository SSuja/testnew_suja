package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.enums.Status;

public interface ConcreteStrengthTestService {
  public List<ConcreteStrengthTest> getAllConcreteStrengthTests();

  boolean isConcreteStrengthTestExist(Long id);

  public ConcreteStrengthTest getConcreteStrengthTestById(Long id);

  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest);

  public void deleteConcreteStrengthTest(Long id);

  public boolean checkConcreteAge(Long concreteAge);

  public Page<ConcreteStrengthTest> searchConcreteStrengthTest(Long finishProductSampleId,
      Status status, Double strengh, Double strenghMin, Double strenghMax, Double strenghGradeRatio,
      Double strenghGradeRatioMin, Double strenghGradeRatioMax, BooleanBuilder booleanBuilder,
      int page, int size);
}

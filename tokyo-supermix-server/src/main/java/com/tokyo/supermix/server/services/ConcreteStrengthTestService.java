package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;

public interface ConcreteStrengthTestService {
  public List<ConcreteStrengthTest> getAllConcreteStrengthTests();

  boolean isConcreteStrengthTestExist(Long id);

  public ConcreteStrengthTest getConcreteStrengthTestById(Long id);

  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest);

  public void deleteConcreteStrengthTest(Long id);

  public boolean checkConcreteAge(Long concreteAge);
}

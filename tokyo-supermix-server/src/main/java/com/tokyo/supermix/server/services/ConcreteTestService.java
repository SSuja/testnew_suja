package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ConcreteTestOld;

public interface ConcreteTestService {
  public ConcreteTestOld saveConcreteTest(ConcreteTestOld concreteTest);

  public List<ConcreteTestOld> getAllConcreteTest();

  public ConcreteTestOld getConcreteTestById(Long id);

  public void deleteConcreteTest(Long id);

  public boolean isConcreteTestExit(Long id);
}

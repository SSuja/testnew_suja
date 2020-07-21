package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TestEquation;

public interface TestEquationService {
  public TestEquation saveTestEquation(TestEquation testEquation);

  public List<TestEquation> getAllTestEquations();

  public boolean isDuplicateEntry(Long testConfigureId, Long equationId);

  public TestEquation getByTestEquationId(Long id);

  public boolean isTestEquationExists(Long id);

  public void deleteTestEquation(Long id);
}

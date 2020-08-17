package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.TestEquationDto;
import com.tokyo.supermix.data.entities.TestEquation;

public interface TestEquationService {
  public TestEquation saveTestEquation(TestEquation testEquation);

  public List<TestEquation> getAllTestEquations();

  public boolean isDuplicateEntry(Long testConfigureId, Long equationId);

  public TestEquation getByTestEquationId(Long id);

  public boolean isTestEquationExists(Long id);

  public void deleteTestEquation(Long id);

  public void saveTestEquationAndTestEquationParameter(TestEquationDto testEquationDto);

  public List<TestEquation> getByTestConfigure(Long testConfigureId);

  public List<TestEquation> getByEquation(Long equationId);

  public boolean isEquationExists(Long equationId);

  public boolean isTestParaneterExists(Long testParameterId);

  public TestEquation getByTestParameter(Long testParameterId);
}

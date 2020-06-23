package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ParameterEquation;

public interface ParameterEquationService {
  public List<ParameterEquation> saveParameterEquation(List<ParameterEquation> parameterEquation);

  public boolean isEquationIdAndTestParameterId(Long equationId, Long testParameterId);

  public List<ParameterEquation> getParameterEquations();

  public ParameterEquation getParameterEquationById(Long id);

  public boolean isParameterEquationExist(Long id);

  public void deleteParameterEquation(Long id);

  public List<ParameterEquation> getParameterEquationByTestParameter(Long testParameterId);

  public ParameterEquation updateParameterEquation(ParameterEquation parameterEquation);
}

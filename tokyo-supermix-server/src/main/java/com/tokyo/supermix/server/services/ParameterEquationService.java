package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.ParameterEquationEleDto;
import com.tokyo.supermix.data.entities.ParameterEquation;

public interface ParameterEquationService {
  public void saveParameterEquation(ParameterEquation parameterEquation);

  public boolean isEquationIdAndTestParameterId(Long equationId, Long testParameterId);

  public List<ParameterEquation> getParameterEquations();

  public ParameterEquation getParameterEquationById(Long id);

  public boolean isParameterEquationExist(Long id);

  public void deleteParameterEquation(Long id);

  public ParameterEquation getParameterEquationByTestParameter(Long testParameterId);

  public ParameterEquation updateParameterEquation(ParameterEquation parameterEquation);

  public void saveParameterEquationAndElement(ParameterEquationEleDto parameterEquationEleDto);

  public List<ParameterEquation> getParameterEquationsByTestConfigureId(Long testConfigureId);

}

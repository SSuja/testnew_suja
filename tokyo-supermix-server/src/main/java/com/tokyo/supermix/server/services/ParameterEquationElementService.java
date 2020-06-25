package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ParameterEquationElement;

public interface ParameterEquationElementService {
  public boolean isDuplicateEntryExist(Long parameterEquationId, Long testParameterId);

  public List<ParameterEquationElement> getAllParameterEquationElements();

  public ParameterEquationElement getByParameterEquationElement(Long id);

  public boolean isExistsById(Long id);

  public List<ParameterEquationElement> getByTestParameterId(Long testParameterId);

  public List<ParameterEquationElement> getByParameterEquationId(Long parameterEquationId);

}

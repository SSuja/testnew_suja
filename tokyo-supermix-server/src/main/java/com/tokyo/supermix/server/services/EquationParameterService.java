package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.EquationParameter;

public interface EquationParameterService {
  public EquationParameter saveEquationParameter(EquationParameter equationParameter);

  public List<EquationParameter> getAllEquationParameters();

  public EquationParameter getEquationParameterById(Long id);

  public boolean isEquationParameterExist(Long id);

  public List<EquationParameter> getEquationByEquationId(Long test);

  public void deleteTestParameter(Long id);

  public boolean isDuplicateRowExists(Long equationId, Long parameterId);
}

package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;

public interface ParameterResultService {
  public List<ParameterResult> getAllParameterResults();

  public void saveParameterValue(ParameterResult parameterValue);

  public void deleteParameterResult(Long id);

  public ParameterResult getParameterResultById(Long id);

  public boolean isParameterResultExist(Long id);

  public void updateMaterialTestTrialResult(MaterialTestTrial materialTestTrial);

  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode);
}
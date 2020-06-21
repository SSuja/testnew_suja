package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.ParameterResultRequestDto;
import com.tokyo.supermix.data.entities.ParameterResult;

public interface ParameterResultService {
  public List<ParameterResult> getAllParameterResults();

  public void saveParameterValue(ParameterResult parameterValue);

  public void deleteParameterResult(Long id);

  public ParameterResult getParameterResultById(Long id);

  public boolean isParameterResultExist(Long id);

  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode);

 // public void isTestParameterValueInConfigureLevel(ParameterResultRequestDto parameterResult);

  public List<ParameterResult> findParameterResultByPlantCode(String plantCode);

  public List<ParameterResult> getParameterResultWithConfigValue(
      String materialTestTrialCode, String materialTestCode);
  public List<ParameterResult> findByMaterialTestCode(String materialTestCode);
}

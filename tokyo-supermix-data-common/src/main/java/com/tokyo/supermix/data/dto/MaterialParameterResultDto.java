package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.entities.ParameterResult;

public class MaterialParameterResultDto {
  private String materialTestCode;
  private String materialTestTrialCode;
  public List<ParameterResult> parameterResults;

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
  }

  public String getMaterialTestTrialCode() {
    return materialTestTrialCode;
  }

  public void setMaterialTestTrialCode(String materialTestTrialCode) {
    this.materialTestTrialCode = materialTestTrialCode;
  }

  public List<ParameterResult> getParameterResults() {
    return parameterResults;
  }

  public void setParameterResults(List<ParameterResult> parameterResults) {
    this.parameterResults = parameterResults;
  }
}

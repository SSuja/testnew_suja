package com.tokyo.supermix.data.dto;

import java.util.List;

public class MaterialParameterResultDto {
	private String materialTestCode;
	private String materialTestTrialCode;
	public List<ParameterResultDto> parameterResults;

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

	public List<ParameterResultDto> getParameterResults() {
		return parameterResults;
	}

	public void setParameterResults(List<ParameterResultDto> parameterResults) {
		this.parameterResults = parameterResults;
	}
}

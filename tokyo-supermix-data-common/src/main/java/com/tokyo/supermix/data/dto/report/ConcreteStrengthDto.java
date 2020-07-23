package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class ConcreteStrengthDto {
	private String cubeCode;
	private List<TestAndResult> testAndResult;

	public String getCubeCode() {
		return cubeCode;
	}

	public void setCubeCode(String cubeCode) {
		this.cubeCode = cubeCode;
	}

	public List<TestAndResult> getTestAndResult() {
		return testAndResult;
	}

	public void setTestAndResult(List<TestAndResult> testAndResult) {
		this.testAndResult = testAndResult;
	}

}

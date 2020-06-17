package com.tokyo.supermix.data.dto;

import java.util.List;

import com.tokyo.supermix.data.dto.report.AcceptedValueDto;

public class TestConfigureDto {
	private Long id;
	private String testType;
	private String testName;
	private boolean coreTest;
	private String description;
	private String testProcedure;
	private String prefix;
	private AcceptedValueDto acceptedValue;
	private String  equation;
	private List<EquationParameterResponseDto> parameters;
	private List<TestParameterDto> testparameters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}		

	public boolean isCoreTest() {
		return coreTest;
	}

	public void setCoreTest(boolean coreTest) {
		this.coreTest = coreTest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTestProcedure() {
		return testProcedure;
	}

	public void setTestProcedure(String testProcedure) {
		this.testProcedure = testProcedure;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public AcceptedValueDto getAcceptedValue() {
		return acceptedValue;
	}

	public void setAcceptedValue(AcceptedValueDto acceptedValue) {
		this.acceptedValue = acceptedValue;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public List<EquationParameterResponseDto> getParameters() {
		return parameters;
	}

	public void setParameters(List<EquationParameterResponseDto> parameters) {
		this.parameters = parameters;
	}

	public List<TestParameterDto> getTestparameters() {
		return testparameters;
	}

	public void setTestparameters(List<TestParameterDto> testparameters) {
		this.testparameters = testparameters;
	}

	
}

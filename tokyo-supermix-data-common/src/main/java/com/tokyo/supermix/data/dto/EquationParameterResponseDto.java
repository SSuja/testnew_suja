package com.tokyo.supermix.data.dto;

public class EquationParameterResponseDto {
	private Long id;
	private Long testParameterId;
	private String testParameterName;
	private String testParameterAbbreviation;
	private String equationFormula;
	private Long equationId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestParameterId() {
		return testParameterId;
	}

	public void setTestParameterId(Long testParameterId) {
		this.testParameterId = testParameterId;
	}

	public String getTestParameterName() {
		return testParameterName;
	}

	public void setTestParameterName(String testParameterName) {
		this.testParameterName = testParameterName;
	}

	public String getTestParameterAbbreviation() {
		return testParameterAbbreviation;
	}

	public void setTestParameterAbbreviation(String testParameterAbbreviation) {
		this.testParameterAbbreviation = testParameterAbbreviation;
	}

	public String getEquationFormula() {
		return equationFormula;
	}

	public void setEquationFormula(String equationFormula) {
		this.equationFormula = equationFormula;
	}

	public Long getEquationId() {
		return equationId;
	}

	public void setEquationId(Long equationId) {
		this.equationId = equationId;
	}

}

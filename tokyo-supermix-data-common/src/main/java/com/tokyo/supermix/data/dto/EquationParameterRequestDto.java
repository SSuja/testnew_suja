package com.tokyo.supermix.data.dto;

public class EquationParameterRequestDto {
	private Long id;
	private Long equationId;
	private Long testParameterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEquationId() {
		return equationId;
	}

	public void setEquationId(Long equationId) {
		this.equationId = equationId;
	}

	public Long getTestParameterId() {
		return testParameterId;
	}

	public void setTestParameterId(Long testParameterId) {
		this.testParameterId = testParameterId;
	}
}

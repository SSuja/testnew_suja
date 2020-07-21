package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.TestParameterType;

public class TestParameterRequestDto {
	private Long id;
	private Long testConfigureId;
	private Long parameterId;
	private Long unitId;
	@NotNull(message = "{testParameterDto.abbreviation.null}")
	@NotEmpty(message = "{testParameterDto.abbreviation.empty}")
	private String abbreviation;
	private TestParameterType type;
	private Double value;
	private boolean equationExists;
	private Long qualityParameterId;
	private boolean acceptedCriteria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestConfigureId() {
		return testConfigureId;
	}

	public void setTestConfigureId(Long testConfigureId) {
		this.testConfigureId = testConfigureId;
	}

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public boolean isEquationExists() {
		return equationExists;
	}

	public void setEquationExists(boolean equationExists) {
		this.equationExists = equationExists;
	}

	public TestParameterType getType() {
		return type;
	}

	public void setType(TestParameterType type) {
		this.type = type;
	}

	public Long getQualityParameterId() {
		return qualityParameterId;
	}

	public void setQualityParameterId(Long qualityParameterId) {
		this.qualityParameterId = qualityParameterId;
	}

	public boolean isAcceptedCriteria() {
		return acceptedCriteria;
	}

	public void setAcceptedCriteria(boolean acceptedCriteria) {
		this.acceptedCriteria = acceptedCriteria;
	}
}

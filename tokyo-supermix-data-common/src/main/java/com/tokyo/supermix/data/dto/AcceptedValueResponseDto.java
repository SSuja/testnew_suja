package com.tokyo.supermix.data.dto;

public class AcceptedValueResponseDto {
	private Long id;
	private Double minValue;
	private Double maxValue;
	private TestResponseDto test;
	private ParameterDto parameter;
	private UnitDto unit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public TestResponseDto getTest() {
		return test;
	}

	public void setTest(TestResponseDto test) {
		this.test = test;
	}

	public ParameterDto getParameter() {
		return parameter;
	}

	public void setParameter(ParameterDto parameter) {
		this.parameter = parameter;
	}

	public UnitDto getUnit() {
		return unit;
	}

	public void setUnit(UnitDto unit) {
		this.unit = unit;
	}
}

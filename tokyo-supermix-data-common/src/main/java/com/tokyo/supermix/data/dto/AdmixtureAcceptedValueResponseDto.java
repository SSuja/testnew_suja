package com.tokyo.supermix.data.dto;

public class AdmixtureAcceptedValueResponseDto {

	private Long id;
	private Double minValue;
	private Double maxValue;
	private TestResponseDto test;
	private ParameterDto parameter;
	private UnitDto unit;
	private RawMaterialResponseDto rawMaterialResponseDto;
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
	public RawMaterialResponseDto getRawMaterialResponseDto() {
		return rawMaterialResponseDto;
	}
	public void setRawMaterialResponseDto(RawMaterialResponseDto rawMaterialResponseDto) {
		this.rawMaterialResponseDto = rawMaterialResponseDto;
	}
	
}

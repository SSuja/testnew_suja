package com.tokyo.supermix.data.dto;

public class MaterialAcceptedValueResponseDto {

	private Long id;
	private Double minValue;
	private Double maxValue;
	private RawMaterialResponseDto rawMaterialResponseDto;
	private TestConfigureResponseDto testConfigure;
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

	public RawMaterialResponseDto getRawMaterialResponseDto() {
		return rawMaterialResponseDto;
	}

	public void setRawMaterialResponseDto(RawMaterialResponseDto rawMaterialResponseDto) {
		this.rawMaterialResponseDto = rawMaterialResponseDto;
	}

	public TestConfigureResponseDto getTestConfigure() {
		return testConfigure;
	}

	public void setTestConfigure(TestConfigureResponseDto testConfigure) {
		this.testConfigure = testConfigure;
	}

	public UnitDto getUnit() {
		return unit;
	}

	public void setUnit(UnitDto unit) {
		this.unit = unit;
	}

}

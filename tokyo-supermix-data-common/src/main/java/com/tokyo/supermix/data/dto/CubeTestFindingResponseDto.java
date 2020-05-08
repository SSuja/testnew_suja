package com.tokyo.supermix.data.dto;

public class CubeTestFindingResponseDto {
	private Long id;
	private Long cubeNo;
	private Long age;
	private Double value;
	private FinishProductSampleResponseDto finishProductSample;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCubeNo() {
		return cubeNo;
	}

	public void setCubeNo(Long cubeNo) {
		this.cubeNo = cubeNo;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public FinishProductSampleResponseDto getFinishProductSample() {
		return finishProductSample;
	}

	public void setFinishProductSample(FinishProductSampleResponseDto finishProductSample) {
		this.finishProductSample = finishProductSample;
	}
}

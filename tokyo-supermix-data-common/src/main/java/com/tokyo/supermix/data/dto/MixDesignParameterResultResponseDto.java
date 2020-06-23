package com.tokyo.supermix.data.dto;

public class MixDesignParameterResultResponseDto {

	private Long id;
	private MixDesignResponseDto mixDesign;
	private RawMaterialResponseDto rawMaterial;
	private MixDesignParameterResponseDto mixDesignParameter;
	private double value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MixDesignResponseDto getMixDesign() {
		return mixDesign;
	}
	public void setMixDesign(MixDesignResponseDto mixDesign) {
		this.mixDesign = mixDesign;
	}
	public RawMaterialResponseDto getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public MixDesignParameterResponseDto getMixDesignParameter() {
		return mixDesignParameter;
	}
	public void setMixDesignParameter(MixDesignParameterResponseDto mixDesignParameter) {
		this.mixDesignParameter = mixDesignParameter;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}

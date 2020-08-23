package com.tokyo.supermix.data.dto;

import java.util.List;

public class AcceptedValueMainDto {
	private Long testConfigureId;
	private Long materialSubCategoryId;
	private String materialSubCategoryName;
	private String testName;
	private Long materialCategoryId;
	private String materialCategoryName;
	private List<AccepetedValueDto> accepetedValueDto;
	public Long getTestConfigureId() {
		return testConfigureId;
	}
	public void setTestConfigureId(Long testConfigureId) {
		this.testConfigureId = testConfigureId;
	}
	public Long getMaterialSubCategoryId() {
		return materialSubCategoryId;
	}
	public void setMaterialSubCategoryId(Long materialSubCategoryId) {
		this.materialSubCategoryId = materialSubCategoryId;
	}
	public String getMaterialSubCategoryName() {
		return materialSubCategoryName;
	}
	public void setMaterialSubCategoryName(String materialSubCategoryName) {
		this.materialSubCategoryName = materialSubCategoryName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Long getMaterialCategoryId() {
		return materialCategoryId;
	}
	public void setMaterialCategoryId(Long materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}
	public String getMaterialCategoryName() {
		return materialCategoryName;
	}
	public void setMaterialCategoryName(String materialCategoryName) {
		this.materialCategoryName = materialCategoryName;
	}
	public List<AccepetedValueDto> getAccepetedValueDto() {
		return accepetedValueDto;
	}
	public void setAccepetedValueDto(List<AccepetedValueDto> accepetedValueDto) {
		this.accepetedValueDto = accepetedValueDto;
	}
}

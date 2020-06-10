package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialSubCategoryRequestDto {
	private Long id;
	@NotNull(message = "{materialSubCategoryRequestDto.name.null}")
	@NotEmpty(message = "{materialSubCategoryRequestDto.name.empty}")
	private String name;
	@NotNull(message = "{materialSubCategoryRequestDto.prefix.null}")
	@NotEmpty(message = "{materialSubCategoryRequestDto.prefix.empty}")
	private String prefix;
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	private Long materialCategoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(Long materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}
}

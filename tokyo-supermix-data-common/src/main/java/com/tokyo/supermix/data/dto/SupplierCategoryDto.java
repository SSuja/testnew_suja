package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class SupplierCategoryDto {

	private Long id;
	@NotNull
	private String category;
	@Null
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

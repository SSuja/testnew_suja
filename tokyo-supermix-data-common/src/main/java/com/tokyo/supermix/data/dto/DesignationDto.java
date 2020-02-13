package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class DesignationDto {

	private Long id;
	@NotNull(message = "{designationDto.name.blank}")
	private String name;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

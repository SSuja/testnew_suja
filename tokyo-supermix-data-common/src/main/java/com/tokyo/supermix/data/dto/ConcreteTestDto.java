package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tokyo.supermix.data.enums.ConcreteType;

public class ConcreteTestDto {

	private Long id;
	@NotNull(message = "{concreteTestDto.name.null}")
	@NotEmpty(message = "{concreteTestDto.name.empty}")
	private String name;
	@NotNull(message = "{concreteTestDto.type.null}")
	private ConcreteType type;

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

	public ConcreteType getType() {
		return type;
	}

	public void setType(ConcreteType type) {
		this.type = type;
	}

}

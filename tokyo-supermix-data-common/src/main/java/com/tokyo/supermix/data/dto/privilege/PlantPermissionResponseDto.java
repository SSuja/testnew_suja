package com.tokyo.supermix.data.dto.privilege;

import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.Permission;

public class PlantPermissionResponseDto {

	private Long id;
	private Permission Permission;
	private Plant plant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return Permission;
	}

	public void setPermission(Permission permission) {
		Permission = permission;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}

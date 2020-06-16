package com.tokyo.supermix.data.dto.privilege;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

public class PlantRolePlantPermissionResponseDto {
	private Long id;
	private boolean status;
	private PlantPermission plantPermission;
	private PlantRole plantRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public PlantPermission getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(PlantPermission plantPermission) {
    this.plantPermission = plantPermission;
  }

  public PlantRole getPlantRole() {
    return plantRole;
  }

  public void setPlantRole(PlantRole plantRole) {
    this.plantRole = plantRole;
  }

	
}

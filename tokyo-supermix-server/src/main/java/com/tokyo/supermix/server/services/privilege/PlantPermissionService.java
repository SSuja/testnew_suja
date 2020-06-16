package com.tokyo.supermix.server.services.privilege;

import java.util.List;

public interface PlantPermissionService {
	public List<String> getPlantsByPermissionName(String permissionName);

}

package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.repositories.privilege.PlantPermissionRepository;

@Service
public class PlantPermissionServiceImpl implements PlantPermissionService {
	@Autowired
	private PlantPermissionRepository plantPermissionRepository;

	@Transactional(readOnly = true)
	public List<String> getPlantsByPermissionName(String permissionName) {

		List<PlantPermission> plantPermissions = plantPermissionRepository.findByPermissionName(permissionName);
		List<String> plants = new ArrayList<>();
		plantPermissions.forEach(plantPermission -> {
			plants.add(plantPermission.getPlant().getCode());
		});
		return plants;
	}

}

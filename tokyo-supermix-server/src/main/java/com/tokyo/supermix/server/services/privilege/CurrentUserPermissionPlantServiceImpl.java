package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.enums.RoleType;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserRoleRepository;
import com.tokyo.supermix.security.UserPrincipal;

@Service
public class CurrentUserPermissionPlantServiceImpl implements CurrentUserPermissionPlantService {
	@Autowired
	PlantRolePlantPermissionServices plantRolePlantPermissionServices;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private UserPlantPermissionService userPlantPermissionService;
	@Autowired
	private PlantRepository plantRepository;
	@Autowired
	private UserPlantRoleRepository userPlantRoleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<String> getPermissionPlantCodeByCurrentUser(UserPrincipal currentUser, String permissionName) {
		List<String> permissionPlantCodes = new ArrayList<String>();
		if (isNonPlantUser(currentUser)) {
			currentUser.getRoles().forEach(roleId -> {
				if (isIndividualRoleType(currentUser, roleId)) {
					userPlantPermissionService
							.getByUserIdAndPermissionNameAndStatus(currentUser.getId(), permissionName, true)
							.forEach(plant -> permissionPlantCodes.add(plant.getCode()));
				}
				if (isGroupRoleType(currentUser, roleId)
						&& rolePermissionService.isPermissionExists(roleId, permissionName)) {
					plantRepository.findAll().forEach(plant -> permissionPlantCodes.add(plant.getCode()));
				}
			});
			return permissionPlantCodes;
		} else {
			currentUser.getPlantRoles().forEach(plantRoleId -> {
				if (isIndividualRoleType(currentUser, plantRoleId)) {
					userPlantPermissionService
							.getByUserIdAndPermissionNameAndStatus(currentUser.getId(), permissionName, true)
							.forEach(plant -> permissionPlantCodes.add(plant.getCode()));
				}
				if (isGroupRoleType(currentUser, plantRoleId)) {
					plantRolePlantPermissionServices
							.getByPlantRoleIdAndPermissionNameAndStatus(plantRoleId, permissionName, true)
							.forEach(plant -> permissionPlantCodes.add(plant.getCode()));
				}
			});
			return permissionPlantCodes;
		}
	}

	private boolean isNonPlantUser(UserPrincipal currentUser) {
		return currentUser.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name()) ? true : false;
	}

	private boolean isIndividualRoleType(UserPrincipal currentUser, Long roleId) {
		return currentUser.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())
				? userRoleRepository.findByRoleIdAndUserId(roleId, currentUser.getId()).getRoleType().name()
						.equalsIgnoreCase(RoleType.INDIVIDUAL.name()) ? true : false
				: userPlantRoleRepository.findByPlantRoleIdAndUserId(roleId, currentUser.getId()).getRoleType().name()
						.equalsIgnoreCase(RoleType.INDIVIDUAL.name()) ? true : false;
	}

	private boolean isGroupRoleType(UserPrincipal currentUser, Long roleId) {
		return currentUser.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())
				? userRoleRepository.findByRoleIdAndUserId(roleId, currentUser.getId()).getRoleType().name()
						.equalsIgnoreCase(RoleType.GROUP.name()) ? true : false
				: userPlantRoleRepository.findByPlantRoleIdAndUserId(roleId, currentUser.getId()).getRoleType().name()
						.equalsIgnoreCase(RoleType.GROUP.name()) ? true : false;
	}
}

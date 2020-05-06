package com.tokyo.supermix.server.services;
import java.util.List;
import com.tokyo.supermix.data.entities.Role;
public interface RoleService {
	  	  void saveRole(Role role);
		List<Role> viewAllRole();
		Long deleteRole(Long id);
		Role findRoleById(Long id);
		Boolean existsByRole(String roleName);
	

}

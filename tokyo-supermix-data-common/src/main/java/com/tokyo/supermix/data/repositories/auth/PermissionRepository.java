package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	public List<Permission> findBySubRouteName(String subRouteName);
	
	public List<Permission> findBySubRouteMainRouteName(String mainRouteName);

}

package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
 List<UserRole> findByRoleId(Long roleId);
 List<UserRole> findByUserId(Long userId);
 boolean existsByRoleId(Long roleId);
 boolean existsByUserId(Long userId);
 UserRole findByRoleIdAndUserId(Long roleId,Long userId);
}

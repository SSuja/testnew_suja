package com.tokyo.supermix.server.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;

public interface UserPlantRoleRepository extends JpaRepository<UserPlantRole, Long>{

}

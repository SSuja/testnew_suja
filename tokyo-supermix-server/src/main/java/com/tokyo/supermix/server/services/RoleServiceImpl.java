package com.tokyo.supermix.server.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Role;
import com.tokyo.supermix.data.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Transactional
	  public void saveRole(Role role) {
	   roleRepository.save(role);
	  }
	
	@Transactional(readOnly = true)
	public List<Role> viewAllRole() {
		return roleRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public Long deleteRole(Long id) {
		if (roleRepository.getOne(id) != null) {
			roleRepository.deleteById(id);
			return id;
		}
		return null;
	}
	@Transactional(readOnly = true)
	public Role findRoleById(Long id) {
		return roleRepository.findById(id).get();
	}
	@Transactional(readOnly = true)
	public Boolean existsByRole(String roleName) {
		return roleRepository.existsByRoleName(roleName);
	}

}

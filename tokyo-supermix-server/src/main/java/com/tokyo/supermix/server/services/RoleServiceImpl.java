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
  private RoleRepository roleRepository;

  @Transactional
  public void saveRole(Role role) {
    roleRepository.save(role);
  }

  @Transactional(readOnly = true)
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRole(Long id) {
    roleRepository.deleteById(id);
  }


  @Transactional(readOnly = true)
  public Role findRoleById(Long id) {
    return roleRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean existsByRoleName(String roleName) {
    return roleRepository.existsByRoleName(roleName);
  }

  @Transactional(readOnly = true)
  public boolean isRoleExists(Long id) {
    return roleRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedRoleExists(Long id, String roleName) {
    if ((!findRoleById(id).getRoleName().equalsIgnoreCase(roleName))
        && (existsByRoleName(roleName))) {
      return true;
    }
    return false;
  }


}
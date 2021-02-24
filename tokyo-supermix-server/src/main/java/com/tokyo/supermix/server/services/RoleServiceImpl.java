package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.auth.QRole;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleRepository roleRepository;

  @Transactional
  public void createRole(Role role) {
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
    return roleRepository.existsByName(roleName);
  }

  @Transactional(readOnly = true)
  public boolean isRoleExists(Long id) {
    return roleRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedRoleExists(Long id, String roleName) {
    if ((!findRoleById(id).getName().equalsIgnoreCase(roleName)) && (existsByRoleName(roleName))) {
      return true;
    }
    return false;
  }

  @Transactional
  public void updateRole(Role role) {
    roleRepository.save(role);
  }

  @Transactional
  public boolean existsByRole(Long roleId) {
    return roleRepository.existsById(roleId);
  }

  @Transactional(readOnly = true)
  public List<Role> getAllWithPagination(Pageable pageable) {
    return roleRepository.findAllByOrderByUpdatedAtDesc(pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long getAllRolesCount() {
    return roleRepository.count();
  }

  @Transactional(readOnly = true)
  public List<Role> searchRoles(String name, BooleanBuilder booleanBuilder,
      Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QRole.role.name.containsIgnoreCase(name));
    }
    pagination.setTotalRecords(
        ((Collection<Role>) roleRepository.findAll(booleanBuilder)).stream().count());
    return (List<Role>) roleRepository.findAll(booleanBuilder);
  }
}

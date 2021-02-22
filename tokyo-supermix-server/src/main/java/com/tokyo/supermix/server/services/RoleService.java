package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface RoleService {
  public void createRole(Role role);

  public void updateRole(Role role);

  public boolean isRoleExists(Long id);

  public List<Role> getAllRoles();

  public void deleteRole(Long id);

  public Role findRoleById(Long id);

  public boolean existsByRoleName(String roleName);

  public boolean isUpdatedRoleExists(Long id, String roleName);

  public boolean existsByRole(Long roleId);

  public List<Role> getAllWithPagination(Pageable pageable);

  public Long getAllRolesCount();

  public List<Role> searchRoles(String name, BooleanBuilder booleanBuilder, Pageable pageable,
      Pagination pagination);
}

package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePermissionDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePrivilegeDto;
import com.tokyo.supermix.data.entities.privilege.MainRoute;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.entities.privilege.SubRoute;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.MainRouteRepository;
import com.tokyo.supermix.data.repositories.privilege.RolePermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubRouteRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private MainRouteRepository mainRouteRepository;
  @Autowired
  private SubRouteRepository subRouteRepository;
  @Autowired
  private Mapper mapper;

  @Transactional
  public void savePrivilege(RolePermission rolePermission) {
    rolePermissionRepository.save(rolePermission);
  }

  @Transactional
  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId) {
    return rolePermissionRepository.findByRoleIdAndPermissionId(permissionId, roleId);
  }

  @Transactional(readOnly = true)
  public List<PrivilegeResponseDto> getRoutePrivilegeByRole(Long roleId) {
    return setPermissionsByRole(roleId);
  }

  private List<PrivilegeDto> setPrivilegeByRole(Long roleId, List<Permission> permissionList,
      List<PrivilegeDto> PrivilegeDtoList) {
    permissionList.forEach(permission -> {
          PrivilegeDto privilegeDto = new PrivilegeDto();
          privilegeDto.setId(permission.getId());
          privilegeDto.setPermission(permission.getName());
          privilegeDto.setStatus(rolePermissionRepository
              .findByRoleIdAndPermissionId(roleId, permission.getId()).isStatus());
          PrivilegeDtoList.add(privilegeDto);
        });
    return PrivilegeDtoList;
  }

  private List<SubRoutePrivilegeDto> setSubRoutesByMainRoute(
      List<SubRoutePrivilegeDto> SubRoutePrivilegeDtoList, Long mainRouteId, Long roleId) {
    subRouteRepository.findByMainRouteId(mainRouteId).forEach(sub -> {
      SubRoutePrivilegeDto subRoutePrivilegeDto = new SubRoutePrivilegeDto();
      subRoutePrivilegeDto.setSubRoute(sub.getName());
      List<PrivilegeDto> PrivilegeDtoList = new ArrayList<PrivilegeDto>();
      List<Permission> PermissionList = permissionRepository.findByRolePermissionRoleIdAndSubRouteId(roleId,sub.getId());
      setPrivilegeByRole(roleId,PermissionList, PrivilegeDtoList);
      subRoutePrivilegeDto.setPrivileges(PrivilegeDtoList);
      SubRoutePrivilegeDtoList.add(subRoutePrivilegeDto);
    });
    return SubRoutePrivilegeDtoList;
  }

  private List<PrivilegeResponseDto> setPermissionsByRole(Long roleId) {
    List<PrivilegeResponseDto> PermissionResponseDtolist = new ArrayList<PrivilegeResponseDto>();
    mainRouteRepository.findAll().forEach(main -> {
      PrivilegeResponseDto privilegeResponseDto = new PrivilegeResponseDto();
      privilegeResponseDto.setMainRoute(main.getName());
      List<SubRoutePrivilegeDto> SubRoutePrivilegeDtoList = new ArrayList<SubRoutePrivilegeDto>();
      setSubRoutesByMainRoute(SubRoutePrivilegeDtoList, main.getId(), roleId);
      privilegeResponseDto.setSubRoutes(SubRoutePrivilegeDtoList);
      PermissionResponseDtolist.add(privilegeResponseDto);
    });
    return PermissionResponseDtolist;
  }

  @Transactional(readOnly = true)
  public List<PermissionResponseDto> getPermissions() {
    return getPermissionsByMainRoute(mainRouteRepository.findAll());
  }

  private List<PermissionResponseDto> getPermissionsByMainRoute(List<MainRoute> MainRouteList) {
    List<PermissionResponseDto> PermissionResponseDtolist = new ArrayList<PermissionResponseDto>();
    MainRouteList.forEach(main -> {
      PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
      permissionResponseDto.setMainRoute(main.getName());
      List<SubRoutePermissionDto> subRoutePermissionDtoList =
          new ArrayList<SubRoutePermissionDto>();
      setPermissiontoSubRoute(main, subRoutePermissionDtoList);
      permissionResponseDto.setSubRoutes(subRoutePermissionDtoList);
      PermissionResponseDtolist.add(permissionResponseDto);
    });
    return PermissionResponseDtolist;
  }

  private void setPermissiontoSubRoute(MainRoute main,
      List<SubRoutePermissionDto> subRoutePermissionDtoList) {
    subRouteRepository.findByMainRouteId(main.getId()).forEach(sub -> {
      SubRoutePermissionDto subRoutePermissionDto = getPermissionsSubRouteName(sub.getName());
      subRoutePermissionDtoList.add(subRoutePermissionDto);
    });
  }

  private SubRoutePermissionDto getPermissionsSubRouteName(String subRouteName) {
    SubRoutePermissionDto subRoutePermissionDto = new SubRoutePermissionDto();
    List<PermissionDto> PermissionDtoList = new ArrayList<PermissionDto>();
    permissionRepository.findBySubRouteName(subRouteName).forEach(permission -> {
      PermissionDto permissionDto = mapper.map(permission, PermissionDto.class);
      PermissionDtoList.add(permissionDto);
    });
    subRoutePermissionDto.setPermissions(PermissionDtoList);
    subRoutePermissionDto.setSubRoute(subRouteName);
    return subRoutePermissionDto;
  }

  @Override
  public List<PermissionDto> getSubRoutesByMainRoute(Long mainRouteId) {
    List<SubRoute> subRoutes = subRouteRepository.findByMainRouteId(mainRouteId);
    List<PermissionDto> permissionDtolist = new ArrayList<PermissionDto>();
    for(SubRoute subRoute:subRoutes) {
      PermissionDto permissionDto = new PermissionDto();
    permissionDto.setId(subRoute.getId());
    permissionDto.setName(subRoute.getName());
    permissionDtolist.add(permissionDto);
    }
    return permissionDtolist;
  }

  @Transactional(readOnly = true)
  public List<MainRoute> getMainRoutes() {
    return mainRouteRepository.findAll();
  }

  @Transactional(readOnly = true)
  public SubRoutePermissionDto getPermissionsBySubRoute(String subRoute) {
    return getPermissionsSubRouteName(subRoute);
  }

  @Transactional(readOnly = true)
  public MainRoute findByMainRouteName(String mainRoute) {
    return mainRouteRepository.findByName(mainRoute);
  }

  @Override
  public List<PrivilegeDto> getPrivilegeByRole(Long roleId) {
    List<PrivilegeDto> PrivilegeDtoList = new ArrayList<PrivilegeDto>();
    List<Permission> PermissionList = permissionRepository.findByRolePermissionRoleId(roleId);
    return setPrivilegeByRole(roleId,PermissionList,PrivilegeDtoList);
  }
}

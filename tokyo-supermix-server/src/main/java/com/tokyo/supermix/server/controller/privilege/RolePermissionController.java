package com.tokyo.supermix.server.controller.privilege;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.RolePermissionService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin
@RestController
public class RolePermissionController {
  @Autowired
  private RolePermissionService RolePermissionService;
  @Autowired
  private Mapper mapper;

  @PutMapping(value = EndpointURI.ROLE_PERMISSION)
  public ResponseEntity<Object> updatePrivilage(
      @RequestBody List<RolePermissionRequestDto> rolePermissionRequestDtos) {
    for (RolePermissionRequestDto RolePermissionRequestDto : rolePermissionRequestDtos) {
      RolePermission rolePermission = RolePermissionService.findByRoleIdAndPermissionId(
          RolePermissionRequestDto.getRoleId(), RolePermissionRequestDto.getPermissionId());
      rolePermission.setStatus(RolePermissionRequestDto.getStatus());
      RolePermissionService.saveRolePermission(rolePermission);
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_ROLE_PERMISSION_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.ROLE_PERMISSIONS)
  public ResponseEntity<Object> getRolePermissionsByRole(@PathVariable("roleId") Long roleId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.ROLE_PERMISSIONS,
            RolePermissionService.getRolePermissionByRole(roleId), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.STATUS_ROLE_PERMISSIONS)
  public ResponseEntity<Object> getRolePermissionsByRoleAndStatus(
      @PathVariable("status") boolean status) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.ROLE_PERMISSIONS,
        mapper.map(RolePermissionService.getRolePermissionByStatus(status), RolePermissionRequestDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.ROLE_PERMISSION_MODULE_STATUS)
  public ResponseEntity<Object> getModuleRolePermissionsByRoleAndModuleStatus(
      @PathVariable("roleId") Long roleId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.ROLE_PERMISSIONS,
            RolePermissionService.getRolePermissionWithModuleByRoleId(roleId), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

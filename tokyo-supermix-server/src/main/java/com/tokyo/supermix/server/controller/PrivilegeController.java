package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.privilege.PrivilegeRequestDto;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.PrivilegeService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin
@RestController
public class PrivilegeController {
  @Autowired
  private PrivilegeService PrivilegeService;
  @Autowired
  private Mapper mapper;
  @PutMapping(value = EndpointURI.PRIVILEGE)
  public ResponseEntity<Object> updatePrivilage(
      @RequestBody List<PrivilegeRequestDto> privilegeRequestDtos) {
    for (PrivilegeRequestDto PrivilegeRequestDto : privilegeRequestDtos) {
      RolePermission rolePermission = PrivilegeService.findByRoleIdAndPermissionId(
          PrivilegeRequestDto.getRoleId(), PrivilegeRequestDto.getPermissionId());
      rolePermission.setStatus(PrivilegeRequestDto.getStatus());
      PrivilegeService.savePrivilege(rolePermission);
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PRIVILEGE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.ROUTE_PRIVILEGES)
  public ResponseEntity<Object> getRoutePrivilegesByRole(@PathVariable("roleId") Long roleId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        PrivilegeService.getRoutePrivilegeByRole(roleId), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.PRIVILEGES)
  public ResponseEntity<Object> getPrivilegesByRole(@PathVariable("roleId") Long roleId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        PrivilegeService.getPrivilegeByRole(roleId), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.STATUS_PRIVILEGES)
  public ResponseEntity<Object> getPrivilegesByRoleAndStatus(@PathVariable("status") boolean status) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
       mapper.map( PrivilegeService.getPrivilegeByStatus(status),PrivilegeRequestDto.class), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.MAIN_ROUTES)
  public ResponseEntity<Object> getallMainRoutes() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MAIN_ROUTES,
        PrivilegeService.getMainRoutes(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUB_ROUTES_BY_MAIN_ROUTE)
  public ResponseEntity<Object> getSubRoutesByMainRoute(@PathVariable String mainRoute) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.SUB_ROUTES,
            PrivilegeService.getSubRoutesByMainRoute(
                PrivilegeService.findByMainRouteName(mainRoute).getId()),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.PERMISSION_BY_SUBROUTE)
  public ResponseEntity<Object> getPrivilegesByRole(@PathVariable("subRouteName") String subRouteName){
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,PrivilegeService.getPermissionsBySubRoute(subRouteName) ,
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  
  @GetMapping(value = EndpointURI.PERMISSIONS)
  public ResponseEntity<Object> getAllPermissions() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        PrivilegeService.getPermissions(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.ROUTE_PERMISSIONS)
  public ResponseEntity<Object> getAllRoutePermissions() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        PrivilegeService.getRoutePermissions(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  @GetMapping(value = "privilege/route/status/{roleId}")
  public ResponseEntity<Object> getRoutePrivilegesByRoleAndRouteStatus(@PathVariable("roleId") Long roleId) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        PrivilegeService.getPrivilegeWithRouteByRoleId(roleId), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }
  
}

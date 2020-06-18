package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.PermissionService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;

@CrossOrigin
@RestController
public class PermissionController {
  @Autowired
  private PermissionService permissionService;

  @GetMapping(value = PrivilegeEndpointURI.PERMISSIONS)
  public ResponseEntity<Object> getAllPermissions() {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PERMISSIONS,
        permissionService.getPermissions(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.PERMISSION_BY_SUBMODULE)
  public ResponseEntity<Object> getRolePermissionsByRole(
      @PathVariable("subModuleName") String subModuleName) {
    return new ResponseEntity<>(
        new ContentResponse<>(PrivilegeConstants.PERMISSIONS,
            permissionService.getPermissionsBySubModule(subModuleName), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

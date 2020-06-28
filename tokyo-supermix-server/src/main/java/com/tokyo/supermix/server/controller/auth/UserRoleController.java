package com.tokyo.supermix.server.controller.auth;

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
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.auth.UserRoleService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UserRoleController {
  @Autowired
  private UserRoleService userRoleService;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @GetMapping(value = PrivilegeEndpointURI.USER_ROLE_BY_USER)
  public ResponseEntity<Object> getRolesByUserId(@PathVariable Long userId) {
    if (userRoleService.existsByUserId(userId)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.ROLE,
          userRoleService.getRolesByUserId(userId), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.USER_ROLE_BY_ROLE)
  public ResponseEntity<Object> getUsersByRoleId(@PathVariable Long roleId) {
    if (userRoleService.existsByRoleId(roleId)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.USERS,
          userRoleService.getUsersByRoleId(roleId), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }
}

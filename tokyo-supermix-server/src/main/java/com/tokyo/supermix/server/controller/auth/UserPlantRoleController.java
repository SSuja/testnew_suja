package com.tokyo.supermix.server.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.auth.UserPlantRoleService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UserPlantRoleController {
  @Autowired
  private UserPlantRoleService userPlantRoleService;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @GetMapping(value = PrivilegeEndpointURI.USER_PLANT_ROLE_BY_USER)
  public ResponseEntity<Object> getRolesByUserId(@PathVariable Long userId) {
    if (!userPlantRoleService.existsByUserId(userId)) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
          privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
        new ContentResponse<>(PrivilegeConstants.ROLE,
            userPlantRoleService.getRolesByUserId(userId), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.USER_PLANT_ROLE_BY_ROLE)
  public ResponseEntity<Object> getUsersByPlantRoleId(@PathVariable Long plantRoleId) {
    if (userPlantRoleService.existsByPlantRoleId(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.ROLE,
              userPlantRoleService.getUsersByPlantRoleId(plantRoleId), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = PrivilegeEndpointURI.USER_PLANT_ROLE_BY_ROLE_BY_USERTYPE)
  public ResponseEntity<Object> getUsersByPlantRoleIdAndUserType(@PathVariable Long plantRoleId,@PathVariable UserType userType) {
    if (userPlantRoleService.existsByPlantRoleId(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.ROLE,
              userPlantRoleService.getUsersByUserTypeAndPlantRoleId(userType, plantRoleId), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }
}

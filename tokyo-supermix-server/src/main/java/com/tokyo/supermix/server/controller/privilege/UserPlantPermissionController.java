package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.UserPlantPermissionService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;

@CrossOrigin(origins = "*")
@RestController
public class UserPlantPermissionController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private UserPlantPermissionService userPlantPermissionService;
  
  @GetMapping(value = PrivilegeEndpointURI.USER_PLANT_PERMISSION_BY_USER_ID)
  public ResponseEntity<Object> getUserPlantPermissionByUserId(
      @PathVariable Long userId) {
    if (userPlantPermissionService.isUserIdExist(userId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
              mapper.map(userPlantPermissionService.getPlantRolePermissionsByUserId(userId), PermissionResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return null;
//        new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
//        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

}

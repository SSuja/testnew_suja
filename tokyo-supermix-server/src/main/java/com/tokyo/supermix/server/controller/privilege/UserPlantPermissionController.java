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
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.UserPlantPermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.UserPrivilegeDto;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.privilege.UserPlantPermissionService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UserPlantPermissionController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private UserPlantPermissionService userPlantPermissionService;
  @Autowired
  PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @GetMapping(value = PrivilegeEndpointURI.USER_PLANT_PERMISSION_BY_USER_ID)
  public ResponseEntity<Object> getUserPlantPermissionByUserId(@PathVariable Long userId) {
    if (userPlantPermissionService.isUserIdExist(userId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
              mapper.map(userPlantPermissionService.getPlantRolePermissionsByUserId(userId),
                  PlantRolePlantPermissionResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_USER_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.USER_PLANT_PERMISSION)
  public ResponseEntity<Object> updateUserPlantPrivilage(
      @RequestBody List<UserPlantPermissionRequestDto> userPlantPermissionRequestDtos) {
    userPlantPermissionService.saveUserPlantPermission(
        mapper.map(userPlantPermissionRequestDtos, UserPlantPermission.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        PrivilegeConstants.UPDATE_PLANT_ROLE_PLANT_PERMISSION_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(
      value = PrivilegeEndpointURI.USER_PLANT_PERMISSION_BY_USER_ID_AND_PLANTCODE_AND_STATUS)
  public ResponseEntity<Object> getUserPlantPermissionByUserIdAndPlantCodeStatus(
      @PathVariable Long userId, @PathVariable String plantCode, @PathVariable Boolean status) {
    if (userPlantPermissionService.isUserIdExist(userId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
              mapper.map(userPlantPermissionService.getByUserIdAndPermissionAndStatus(userId, plantCode, status),
                  UserPrivilegeDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_USER_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }


}

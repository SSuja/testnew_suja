package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class PlantRolePlantPermissionController {

  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantRolePlantPermissionServices plantRolePlantPermissionServices;
  
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @GetMapping(
      value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_SUBMODULE_ID)
  public ResponseEntity<Object> getAllRolePlantPermission(@PathVariable Long plantRoleId,
      @PathVariable Long subModuleId) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PLANT_ROLE_PLANT_PERMISSIONS,
            mapper.map(plantRolePlantPermissionServices
                .getRolePlantPermissionsByPlantRoleIdAndSubModuleID(plantRoleId, subModuleId),
                PlantRolePlantPermissionResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }


  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_STATUS)
  public ResponseEntity<Object> getAllRolePlantPermissionStatus(@PathVariable Long plantRoleId,
      @PathVariable Boolean status) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ROLE_PLANT_PERMISSIONS,
        mapper.map(plantRolePlantPermissionServices.getByPlantRoleIdAndStatus(plantRoleId, status),
            PlantRolePlantPermissionResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }


  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID)
  public ResponseEntity<Object> getPlantRolePlantPermissionByPlantRoleId(
      @PathVariable Long plantRoleId) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_PERMISSIONS,
        mapper.map(
            plantRolePlantPermissionServices.getPlantRolePermissionsByPlantRoleId(plantRoleId),
            PlantRolePlantPermissionDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ROLE_ID,
        validationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }
}

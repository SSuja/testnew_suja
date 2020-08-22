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
import com.tokyo.supermix.data.dto.privilege.PlantResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionRequestDto;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;



@CrossOrigin(origins = "*")
@RestController
public class PlantRolePlantPermissionController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantRolePlantPermissionServices plantRolePlantPermissionServices;

  @Autowired
  PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @GetMapping(
      value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_SUBMODULE_ID)
  public ResponseEntity<Object> getAllRolePlantPermission(@PathVariable Long plantRoleId,
      @PathVariable Long subModuleId) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_ROLE_PLANT_PERMISSIONS,
              mapper.map(
                  plantRolePlantPermissionServices
                      .getRolePlantPermissionsByPlantRoleIdAndSubModuleID(plantRoleId, subModuleId),
                  PlantRolePlantPermissionDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_STATUS)
  public ResponseEntity<Object> getAllRolePlantPermissionStatus(@PathVariable Long plantRoleId,
      @PathVariable Boolean status) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_ROLE_PLANT_PERMISSIONS,
              mapper.map(
                  plantRolePlantPermissionServices.getByPlantRoleIdAndStatus(plantRoleId, status),
                  PlantRolePlantPermissionDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID)
  public ResponseEntity<Object> getPlantRolePlantPermissionByPlantRoleId(
      @PathVariable Long plantRoleId) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
              mapper.map(plantRolePlantPermissionServices.getPlantRolePermissionsByPlantRoleId(
                  plantRoleId), PlantRolePlantPermissionDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(
      value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_SUBMODULE_ID_AND_STATUS)
  public ResponseEntity<Object> getAllRolePlantPermissionStatusAndSubModule(
      @PathVariable Long plantRoleId, @PathVariable Long subModuleId,
      @PathVariable Boolean status) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_ROLE_PLANT_PERMISSIONS,
              mapper.map(plantRolePlantPermissionServices
                  .getRolePlantPermissionsByPlantRoleIdAndSubModuleIDAndStatus(plantRoleId,
                      subModuleId, status),
                  PlantRolePlantPermissionDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PERMISSION_MODULE_STATUS)
  public ResponseEntity<Object> getModulePlantRolePermissionsByRoleAndModuleStatus(
      @PathVariable("plantRoleId") Long plantRoleId) {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
        plantRolePlantPermissionServices.getPlantRolePermissionWithModuleByRoleId(plantRoleId),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }


  @PutMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION)
  public ResponseEntity<Object> updatePlantPrivilage(
      @RequestBody List<PlantRolePlantPermissionRequestDto> plantRolePlantPermissionRequestDtos) {
    plantRolePlantPermissionServices.savePlantRolePlantPermission(
        mapper.map(plantRolePlantPermissionRequestDtos, PlantRolePlantPermission.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        PrivilegeConstants.UPDATE_PLANT_ROLE_PLANT_PERMISSION_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(
      value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_PLANT_CODE)
  public ResponseEntity<Object> getPlantRolePlantPermissionByPlantRoleIdandPlantCode(
      @PathVariable Long plantRoleId, @PathVariable String plantCode) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      if (plantRolePlantPermissionServices.isPlantPermissionPlantCodeExist(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
            mapper.map(plantRolePlantPermissionServices
                .getPlantRolePermissionsByPlantRoleIdAndPlantPermissionPlantCode(plantRoleId,
                    plantCode),
                PlantRolePlantPermissionRequestDto.class),
            RestApiResponseStatus.OK), null, HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
          privilegeValidationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(
      value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION_BY_PLANT_ROLE_ID_AND_PERMISSION_NAME_AND_STATUS)
  public ResponseEntity<Object> getAllPlantsByPlantRoleIdAndPermissionName(
      @PathVariable Long plantRoleId, @PathVariable String permissionName,
      @PathVariable Boolean status) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
      return new ResponseEntity<>(new ContentResponse<>(
          PrivilegeConstants.PLANT_ROLE_PLANT_PERMISSIONS,
          mapper.map(plantRolePlantPermissionServices.getByPlantRoleIdAndPermissionNameAndStatus(
              plantRoleId, permissionName, status), PlantResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PERMISSION_MODULE_STATUS_USER_ID)
  public ResponseEntity<Object> getModulePlantRolePermissionsByRoleAndModuleStatuswithCombine(
      @PathVariable Long userId,  @PathVariable String plantCode) {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS,
        plantRolePlantPermissionServices.getCombine(userId, plantCode),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  @PutMapping(value = PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSIONS_BY_PLANT_ROLE_ID_AND_STATUS)
  public ResponseEntity<Object> updateAllPlantPrivilage(@PathVariable Long plantRoleId,@PathVariable Boolean status ) {
    if (plantRolePlantPermissionServices.isPlantRoleIdExist(plantRoleId)) {
    plantRolePlantPermissionServices.UpdatePlantRolePlantPermission(plantRoleId,status);
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        PrivilegeConstants.UPDATE_PLANT_ROLE_PLANT_PERMISSION_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_ROLE_ID,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }
}

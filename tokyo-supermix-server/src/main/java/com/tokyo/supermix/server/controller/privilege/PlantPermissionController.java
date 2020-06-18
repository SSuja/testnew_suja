package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantPermissionResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.privilege.PlantPermissionService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class PlantPermissionController {
  @Autowired
  private PlantPermissionService plantPermissionService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @GetMapping(value = PrivilegeEndpointURI.PLANT_PERMISSION_BY_PERMISSION_NAME)
  public ResponseEntity<Object> getPlantsFindingByPermissionName(
      @PathVariable String permissionName) {
    if(plantPermissionService.isPermissionNameExists(permissionName)) {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSION,
        (plantPermissionService.getPlantsByPermissionName(permissionName)),
        RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_PERMISSION_NAME,
        privilegeValidationFailureStatusCodes.getPlantPermissionNotExists()), HttpStatus.BAD_REQUEST);
 
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_PERMISSIONS)
  public ResponseEntity<Object> getAllPlantPermissions() {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS, mapper
        .map(plantPermissionService.getAllPlantsByPermissions(), PlantPermissionResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  
  @GetMapping(value = PrivilegeEndpointURI.PLANT_PERMISSION_BY_PLANT_CODE_AND_SUBMODULE_ID_AND_MAINMODULE_ID)
  public ResponseEntity<Object> getAllPlantPermissionByPlantCode(@PathVariable String plantCode,
      @PathVariable Long subModuleId,  @PathVariable Long mainModuleId) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.PLANT_PERMISSIONS ,       
              mapper.map(
                 plantPermissionService.getPlantPermissionByPlantCodeAndMainModuleAndSubModule(plantCode, subModuleId, mainModuleId),
                  PlantPermissionResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
   
  }

}

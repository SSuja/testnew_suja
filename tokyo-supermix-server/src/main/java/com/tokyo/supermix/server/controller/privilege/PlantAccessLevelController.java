package com.tokyo.supermix.server.controller.privilege;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantAccessLevelDto;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantAccessLevelService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class PlantAccessLevelController {
  @Autowired
  private PlantAccessLevelService plantAccessLevelService;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;
  @Autowired
  private Mapper mapper;

  @PostMapping(value = PrivilegeEndpointURI.PLANT_ACCESS_LEVEL)
  public ResponseEntity<Object> createPlantAccessLevel(
      @RequestBody PlantAccessLevelDto plantAccessLevelDto) {
    if (plantAccessLevelService.existsByPlantCodeAndPlantRoleId(plantAccessLevelDto.getPlantCode(),
        plantAccessLevelDto.getPlantRoleId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(PrivilegeConstants.PLANT_ACCESS_LEVEL,
              privilegeValidationFailureStatusCodes.getRoleAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    plantAccessLevelService
        .savePlantRolePlantCode(mapper.map(plantAccessLevelDto, PlantAccessLevel.class));
    return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
        PrivilegeConstants.ADD_PLANT_ACCESS_ROLE_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_BY_PLANT_CODE_AND_STATUS)
  public ResponseEntity<Object> getPlantRolesByPlantCodeAndStatus(@PathVariable String plantCode,
      @PathVariable boolean status) {
    if (plantAccessLevelService.existsByPlantCodeAndStatus(plantCode, status)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_ACCESS_LEVELS,
          mapper.map(plantAccessLevelService.getPlantRolesByPlantCodeAndStatus(plantCode, status),
              PlantAccessLevelDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(PrivilegeConstants.PLANT_ACCESS_LEVEL,
            privilegeValidationFailureStatusCodes.getPlantAccessLevelNotExists()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.PLANT_ACCESS_LEVEL)
  public ResponseEntity<Object> updatePlantAccessLevel(
      @RequestBody PlantAccessLevelDto plantAccessLevelDto) {
    plantAccessLevelService.statusUpdate(plantAccessLevelDto.getPlantCode(),
        plantAccessLevelDto.getPlantRoleId());
    return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
        PrivilegeConstants.ADD_PLANT_ACCESS_ROLE_SUCCESS), HttpStatus.OK);
  }
}


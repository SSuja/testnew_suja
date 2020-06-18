package com.tokyo.supermix.server.controller.privilege;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.RoleService;
import com.tokyo.supermix.server.services.privilege.PlantRoleService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;


@RestController
public class PlantRoleController {
  @Autowired
  private PlantRoleService plantRoleService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @PostMapping(value = PrivilegeEndpointURI.PLANT_ROLE)
  public ResponseEntity<Object> createParameter(@Valid @RequestBody PlantRoleDto plantRoleDto) {
    if (roleService.existsByRole(plantRoleDto.getRoleId())) {
      if (plantService.isPlantExist(plantRoleDto.getPlantCode())) {
        if (plantRoleService.existsByPlantCodeAndRoleId(plantRoleDto.getPlantCode(),
            plantRoleDto.getRoleId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(PrivilegeConstants.ROLE,
                  privilegeValidationFailureStatusCodes.getRoleAlreadyExists()),
              HttpStatus.BAD_REQUEST);
        }
        plantRoleService.savePlantRole(mapper.map(plantRoleDto, PlantRole.class));
        return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
            PrivilegeConstants.ADD_PLANT_ROLE_SUCCESS), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
          privilegeValidationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE_ID,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }
}


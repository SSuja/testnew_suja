package com.tokyo.supermix.server.controller.privilege;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.privilege.PlantRoleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class PlantRoleController {
  @Autowired
  private PlantRoleService plantRoleService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @PostMapping(value = EndpointURI.PLANT_ROLE)
  public ResponseEntity<Object> createParameter(@Valid @RequestBody PlantRoleDto plantRoleDto) {
    if (plantRoleService.existsByPlantCodeAndRoleId(plantRoleDto.getPlantCode(),
        plantRoleDto.getRoleId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.ROLE,
          validationFailureStatusCodes.getRoleAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    plantRoleService.savePlantRole(mapper.map(plantRoleDto, PlantRole.class));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PLANT_ROLE_SUCCESS),
        HttpStatus.OK);
  }
}

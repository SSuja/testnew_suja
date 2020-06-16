package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantPermissionResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.PlantPermissionService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin(origins = "*")
@RestController
public class PlantPermissionController {
  @Autowired
  private PlantPermissionService plantPermissionService;
  @Autowired
  private Mapper mapper;

  @GetMapping(value = EndpointURI.PLANT_PERMISSION_BY_PERMISSION_NAME)
  public ResponseEntity<Object> getPlantsFindingByPermissionName(
      @PathVariable String permissionName) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_PERMISSION,
        (plantPermissionService.getPlantsByPermissionName(permissionName)),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PLANT_PERMISSIONS)
  public ResponseEntity<Object> getAllPlantPermissions() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_PERMISSIONS, mapper
        .map(plantPermissionService.getAllPlantsByPermissions(), PlantPermissionResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantAccessLevelService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.PlantPermissionService;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;
import com.tokyo.supermix.server.services.privilege.PlantRoleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class PlantController {
  @Autowired
  PlantService plantService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantRoleService plantRoleService;
  @Autowired
  private PlantPermissionService plantPermissionService;
  @Autowired
  private PlantAccessLevelService plantAccessLevelService;
  @Autowired
  private PlantRolePlantPermissionServices plantRolePlantPermissionServices;
  private static final Logger logger = Logger.getLogger(PlantController.class);

  @PostMapping(value = EndpointURI.PLANT)
  @PreAuthorize("hasAuthority('add_plant')")
  public ResponseEntity<Object> createPlant(@Valid @RequestBody PlantDto plantDto) {
    if (plantService.isPlantNameExist(plantDto.getName())) {
      logger.debug("PlantName already exists: createPlant(), plantName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
          validationFailureStatusCodes.getPlantNameAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (plantService.isPlantExist(plantDto.getCode())) {
      logger.debug("PlantId already exists: createPlant(), plantId: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantIdAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Plant plant = plantService.savePlant(mapper.map(plantDto, Plant.class));
    PlantRole plantRoleObj = plantRoleService.savePlantRole(plant.getCode(), 1L);
    plantAccessLevelService.createPlantAccessLevel(plantRoleObj);
    plantPermissionService.savePlantPermission(plant);
    plantRolePlantPermissionServices.createPlantRolePlantPermission(plantRoleObj);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PLANT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PLANTS)
  @PreAuthorize("hasAuthority('get_plant')")
  public ResponseEntity<Object> getAllPlants() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PLANTS,
            mapper.map(plantService.getAllPlants(), PlantDto.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // get plant by id
  @GetMapping(value = EndpointURI.GET_PLANT_BY_CODE)
  public ResponseEntity<Object> getPlantByCode(@PathVariable String code) {
    if (plantService.isPlantExist(code)) {
      logger.debug("Get plant by plantCode ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT,
          mapper.map(plantService.getPlantByCode(code), PlantDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PLANT)
  @PreAuthorize("hasAuthority('edit_plant')")
  public ResponseEntity<Object> updatePlant(@Valid @RequestBody PlantDto plantDto) {
    if (plantService.isPlantExist(plantDto.getCode())) {
      if (plantService.isUpdatedPlantNameExist(plantDto.getCode(), plantDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
            validationFailureStatusCodes.getPlantAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      plantService.savePlant(mapper.map(plantDto, Plant.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.DELETE_PLANT_BY_CODE)
  @PreAuthorize("hasAuthority('delete_plant')")
  public ResponseEntity<Object> deletePlant(@PathVariable String code) {
    if (plantService.isPlantExist(code)) {
      plantService.deletePlant(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PLANT_DELETED), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_PLANT)
  public ResponseEntity<Object> getPlantSearch(
      @QuerydslPredicate(root = Plant.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTS,
        plantService.searchPlant(predicate, size, page), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }
}

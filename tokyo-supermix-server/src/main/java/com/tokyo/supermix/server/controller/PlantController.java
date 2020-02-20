package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.DesignationDto;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class PlantController {
  @Autowired
  PlantService plantService;

  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(PlantController.class);

  @PostMapping(value = EndpointURI.PLANT)
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
    Plant plant = mapper.map(plantDto, Plant.class);
    plantService.savePlant(plant);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PLANT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PLANTS)
  public ResponseEntity<Object> getAllPlants() {
    List<Plant> plantList = plantService.getAllPlants();
    List<PlantDto> plantDtoList = mapper.map(plantList, PlantDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PLANTS, plantDtoList, RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

  // get designation by id
  @GetMapping(value = EndpointURI.GET_PLANT_BY_CODE)
  public ResponseEntity<Object> getPlantByCode(@PathVariable String code) {
    if (plantService.isPlantExist(code)) {
      logger.debug("Get plant by plantCode ");
      PlantDto plantDto = mapper.map(plantService.getPlantByCode(code), PlantDto.class);
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT,
          mapper.map(plantDto, DesignationDto.class), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PLANT)
  public ResponseEntity<Object> updatePlant(@Valid @RequestBody PlantDto plantDto) {
    if (plantService.isPlantExist(plantDto.getCode())) {
      if (plantService.isUpdatedPlantNameExist(plantDto.getCode(), plantDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
            validationFailureStatusCodes.getPlantAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      Plant plant = mapper.map(plantDto, Plant.class);
      plantService.savePlant(plant);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

}

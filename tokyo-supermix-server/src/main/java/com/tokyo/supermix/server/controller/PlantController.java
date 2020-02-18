package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
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
    if (plantService.isPlantAlreadyExist(plantDto.getName())) {
      logger.debug("Plant already exists: createPlant(), plantName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
          validationFailureStatusCodes.getPlantAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Plant plant = mapper.map(plantDto, Plant.class);
    plantService.createPlant(plant);
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
  
  @PutMapping(value = EndpointURI.UPDATE_PLANT)
  public ResponseEntity<Object> updatePlant(@Valid @RequestBody PlantDto plantDto) {
    if (plantService.isPlantExist(plantDto.getCode())) {
      Plant plantData = plantService.getByCode(plantDto.getCode());
      if (plantData.getName().equalsIgnoreCase(plantDto.getName())) {
        Plant plant = mapper.map(plantDto, Plant.class);
        plantService.updatePlant(plant);
        return new ResponseEntity<>(
            new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANT_SUCCESS),
            HttpStatus.OK);
      }
      if (plantService.isPlantAlreadyExist(plantDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
            validationFailureStatusCodes.getPlantAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      Plant plant = mapper.map(plantDto, Plant.class);
      plantService.updatePlant(plant);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

}

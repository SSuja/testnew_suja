package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

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
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
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
    plantService.savePlant(mapper.map(plantDto, Plant.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PLANT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PLANTS)
  public ResponseEntity<Object> getAllPlants(@CurrentUser UserPrincipal currentUser) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTS,
        mapper.map(plantService.getAllPlants(currentUser), PlantDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // get plant by id
  @GetMapping(value = EndpointURI.PLANT_BY_CODE)
  public ResponseEntity<Object> getPlantByCode(@PathVariable String code,
      @CurrentUser UserPrincipal currentUser) {
    if (code.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTS,
          mapper.map(plantService.getAllPlants(currentUser), PlantDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    if (plantService.isPlantExist(code)) {
      if (currentUserPermissionPlantService
          .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PLANT)
          .contains(code)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTS,
            mapper.map(plantService.getPlantByCode(code), PlantDto.class),
            RestApiResponseStatus.OK), null, HttpStatus.OK);
      }
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
      plantService.editPlant(mapper.map(plantDto, Plant.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_NAME,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.PLANT_BY_CODE)
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

  @GetMapping(value = EndpointURI.PLANT_PAGINATION)
  public ResponseEntity<Object> getAllPlantsForPagination(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(plantService.countPlant());
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PLANT,
        mapper.map(plantService.getAllPlantByPageable(pageable), PlantDto.class),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SEARCH_PLANT)
  public ResponseEntity<Object> searchPlant(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "address", required = false) String address,
      @RequestParam(name = "subBusinessUnitName", required = false) String subBusinessUnitName,
      @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PLANT,
        mapper.map(plantService.searchPlant(code, name, address, subBusinessUnitName, phoneNumber,
            booleanBuilder, page, size, pageable, pagination), PlantDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}

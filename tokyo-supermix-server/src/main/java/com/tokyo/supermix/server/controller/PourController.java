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
import com.tokyo.supermix.data.dto.PourDtoRequest;
import com.tokyo.supermix.data.dto.PourDtoResponse;
import com.tokyo.supermix.data.entities.Pour;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.PourService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class PourController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private PourService pourService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(PourController.class);

  @PostMapping(value = EndpointURI.POUR)
  @PreAuthorize("hasAuthority('add_pour')")
  public ResponseEntity<Object> createPour(@Valid @RequestBody PourDtoRequest pourDtoRequest) {
    if (pourService.isPourNameExistBYProject(pourDtoRequest.getName(),
        pourDtoRequest.getProjectCode())) {
      return new ResponseEntity<Object>(new ValidationFailureResponse(Constants.POUR,
          validationFailureStatusCodes.getPourAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    pourService.savePour(mapper.map(pourDtoRequest, Pour.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_POUR_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_POUR_BY_ID)
  public ResponseEntity<Object> getPourById(@PathVariable Long id) {
    if (pourService.isPourExit(id)) {
      logger.debug("get pour By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.POUR,
          mapper.map(pourService.getPourById(id), PourDtoResponse.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<Object>(new ValidationFailureResponse(Constants.POUR,
        validationFailureStatusCodes.getPourNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.POURS)
  @PreAuthorize("hasAuthority('get_pour')")
  public ResponseEntity<Object> getAllPour() {
    logger.debug("gat all pour");
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.POUR,
            mapper.map(pourService.getAllPour(), PourDtoResponse.class), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.DELETE_POUR)
  @PreAuthorize("hasAuthority('delete_pour')")
  public ResponseEntity<Object> deletePour(@PathVariable Long id) {
    if (pourService.isPourExit(id)) {
      logger.debug("get pour By Id");
      pourService.deletePour(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.POUR_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.POUR,
        validationFailureStatusCodes.getPourNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.POUR)
  @PreAuthorize("hasAuthority('edit_pour')")
  public ResponseEntity<Object> updatePour(@Valid @RequestBody PourDtoRequest pourDtoRequest) {
    if (pourService.isPourExit(pourDtoRequest.getId())) {
      if (pourService.isUpdatedPourExists(pourDtoRequest.getId(), pourDtoRequest.getName(),
          pourDtoRequest.getProjectCode())) {
        return new ResponseEntity<Object>(new ValidationFailureResponse(Constants.POUR,
            validationFailureStatusCodes.getPourAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      pourService.savePour(mapper.map(pourDtoRequest, Pour.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_POUR_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.POUR,
        validationFailureStatusCodes.getPourNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.POUR_SEARCH)
  public ResponseEntity<Object> getPourSearch(
      @QuerydslPredicate(root = Pour.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.POUR,
        pourService.searchPour(predicate, page, size), RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_POURS_BY_PLANT_CODE)
  public ResponseEntity<Object> getPourByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.POUR,
          mapper.map(pourService.getPoursByPlantCode(plantCode), PourDtoResponse.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

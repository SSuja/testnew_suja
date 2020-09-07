package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PourDtoRequest;
import com.tokyo.supermix.data.dto.PourDtoResponse;
import com.tokyo.supermix.data.entities.Pour;
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
import com.tokyo.supermix.server.services.PourService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

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
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(PourController.class);

  @PostMapping(value = EndpointURI.POUR)
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

  @GetMapping(value = EndpointURI.POUR_BY_ID)
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
  public ResponseEntity<Object> getAllPour() {
    logger.debug("gat all pour");
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.POUR,
            mapper.map(pourService.getAllPour(), PourDtoResponse.class), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.POUR_BY_ID)
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

  @GetMapping(value = EndpointURI.POUR_BY_PLANT)
  public ResponseEntity<Object> getAllPourByPlant(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(pourService.getCountPour());
      logger.debug("gat all pour");
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.POUR,
          mapper.map(pourService.getAllPourByPlant(currentUser, pageable), PourDtoResponse.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_POUR)
        .contains(plantCode)) {
      pagination.setTotalRecords(pourService.getCountPourByPlantCode(plantCode));
      logger.debug("gat all pour");
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.POUR,
          mapper.map(pourService.getPoursByPlantCode(plantCode, pageable), PourDtoResponse.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_POURS_BY_PLANT)
  public ResponseEntity<Object> getPourNameSearch(@PathVariable String plantCode,
      @RequestParam(name = "name") String name) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.POUR,
          mapper.map(pourService.getPourName(name), PourDtoResponse.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT,
        mapper.map(pourService.getPourNameByPlantCode(plantCode, name), PourDtoResponse.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
}

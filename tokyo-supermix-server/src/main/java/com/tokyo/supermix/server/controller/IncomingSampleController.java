package com.tokyo.supermix.server.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.tokyo.supermix.data.dto.IncomingSampleRequestDto;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class IncomingSampleController {
  @Autowired
  private IncomingSampleService incomingSampleService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantService plantService;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(IncomingSampleController.class);

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES)
  public ResponseEntity<Object> getIncomingSamples() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
        mapper.map(incomingSampleService.getAllIncomingSamples(), IncomingSampleResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_PLANT)
  public ResponseEntity<Object> getIncomingSamplesByUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getAllIncomingSamplesByCurrentUser(currentUser),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_INCOMING_SAMPLE)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getIncomingSampleByPlantCode(plantCode),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE)
  public ResponseEntity<Object> getIncomingSampleByCode(@PathVariable String code) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLE, mapper
          .map(incomingSampleService.getIncomingSampleById(code), IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.INCOMING_SAMPLE)
  public ResponseEntity<Object> createIncomingSample(
      @Valid @RequestBody IncomingSampleRequestDto incomingSampleRequestDto) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleRequestDto.getCode())) {
      logger.debug(
          "IncomingSampleCode already exists: createIncomingSample(), IncomingSampleCode: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
              validationFailureStatusCodes.getIncomingSampleAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    incomingSampleService
        .createIncomingSample(mapper.map(incomingSampleRequestDto, IncomingSample.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_INCOMING_SAMPLE_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.INCOMING_SAMPLE)
  public ResponseEntity<Object> updateIncomingSample(
      @Valid @RequestBody IncomingSampleRequestDto incomingSampleRequestDto) {
    if (incomingSampleService.isIncomingSampleExist(incomingSampleRequestDto.getCode())) {
      incomingSampleService
          .updateIncomingSample(mapper.map(incomingSampleRequestDto, IncomingSample.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_INCOMING_SAMPLE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE)
  public ResponseEntity<Object> deleteIncomingSampleByCode(@PathVariable String code) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      incomingSampleService.deleteIncomingSample(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.INCOMING_SAMPLE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.INCOMING_SAMPLE_BY_CODE_AND_STATUS)
  public ResponseEntity<Object> updateStatusIncomingSample(@PathVariable String code,
      @PathVariable Status status) {
    if (incomingSampleService.isIncomingSampleExist(code)) {
      incomingSampleService.updateStatusForIncomingSample(code, status);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_STATUS_INCOMING_SAMPLE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_BY_STATUS)
  public ResponseEntity<Object> getIncomingSampleByStatus(@PathVariable Status status) {
    if (incomingSampleService.isIncomingSampleStatusExist(status)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLE,
          mapper.map(incomingSampleService.getIncomingSampleByStatus(status),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_STATUS,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLE_SEARCH)
  public ResponseEntity<Object> getIncomingSampleSearch(
      @QuerydslPredicate(root = IncomingSample.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
        incomingSampleService.searchIncomingSample(predicate, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_PLANT_CODE)
  public ResponseEntity<Object> getIncomingSampleByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getIncomingSampleByPlantCode(plantCode),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getIncomingSampleMaterialSubCategory(
      @PathVariable Long materialSubCategoryId, @PathVariable String plantCode) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategory(materialSubCategoryId),
                IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
            mapper.map(incomingSampleService.getByMaterialSubCategoryPlantWise(
                materialSubCategoryId, plantCode), IncomingSampleResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = EndpointURI.INCOMING_SAMPLES_BY_TEST_ID)
  public ResponseEntity<Object> getIncomingSampleByPlantCode(@PathVariable Long testId) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.INCOMING_SAMPLES,
          mapper.map(incomingSampleService.getIncomingSampleBytestId(testId),
              IncomingSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
  }
}

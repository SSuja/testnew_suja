package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.FinenessModulusRequestDto;
import com.tokyo.supermix.data.dto.FinenessModulusResponseDto;
import com.tokyo.supermix.data.entities.FinenessModulus;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinenessModulusService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin("*")
public class FinenessModulusController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinenessModulusService finenessModulusService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(FinenessModulusController.class);

  // post API for
  @PostMapping(value = EndpointURI.FINENESS_MODULUS)
  public ResponseEntity<Object> createFinenessModulus(
      @Valid @RequestBody FinenessModulusRequestDto finenessModulusRequestDto) {
    finenessModulusService
        .saveFinenessModulus(mapper.map(finenessModulusRequestDto, FinenessModulus.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINENESS_MODULUS_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINENESS_MODULUS)
  public ResponseEntity<Object> getAllFinenessModuluss() {
    List<FinenessModulus> finenessModulusTestList = finenessModulusService.getAllFinenessModulus();
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.FINENESS_MODULUS,
        mapper.map(finenessModulusTestList, FinenessModulusResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // get FinenessModulus by id
  @GetMapping(value = EndpointURI.FINENESS_MODULUS_BY_ID)
  public ResponseEntity<Object> getFinenessModulusById(@PathVariable Long id) {
    if (finenessModulusService.isFinenessModulusExists(id)) {
      logger.debug("Get FinenessModulus by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINENESS_MODULUS, mapper
          .map(finenessModulusService.getFinenessModulusById(id), FinenessModulusResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINENESS_MODULUS_ID,
        validationFailureStatusCodes.getFinenessModulusNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get FinenessModulus Delete
  @DeleteMapping(value = EndpointURI.FINENESS_MODULUS_BY_ID)
  public ResponseEntity<Object> deleteFinenessModulus(@PathVariable Long id) {
    if (finenessModulusService.isFinenessModulusExists(id)) {
      finenessModulusService.deleteFinenessModulus(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINENESS_MODULUS_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid FinenessModulusId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINENESS_MODULUS_ID,
        validationFailureStatusCodes.getFinenessModulusNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINENESS_MODULUS)
  public ResponseEntity<Object> updateFinenessModulus(
      @Valid @RequestBody FinenessModulusRequestDto FinenessModulusRequestDto) {
    if (finenessModulusService.isFinenessModulusExists(FinenessModulusRequestDto.getId())) {
      finenessModulusService
          .saveFinenessModulus(mapper.map(FinenessModulusRequestDto, FinenessModulus.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_FINENESS_MODULUS_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINENESS_MODULUS_ID,
        validationFailureStatusCodes.getFinenessModulusNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINENESS_MODULUS_BY_MATERIALSUBCATEGORY)
  public ResponseEntity<Object> getFinenessModulusByMaterialSubCategory(
      @PathVariable Long materialSubCategoryId) {
    if (finenessModulusService.isMaterialSubCategoryIdExists(materialSubCategoryId)) {
      logger.debug("Get FinenessModulus by MaterialSubCategoryId ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINENESS_MODULUS,
              mapper.map(finenessModulusService.getFinenessModulusByMaterialSubCategory(
                  materialSubCategoryId), FinenessModulusResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }
}

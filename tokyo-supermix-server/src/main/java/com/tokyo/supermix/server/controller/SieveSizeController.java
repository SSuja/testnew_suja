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
import com.tokyo.supermix.data.dto.SieveSizeRequestDto;
import com.tokyo.supermix.data.dto.SieveSizeResponseDto;
import com.tokyo.supermix.data.entities.SieveSize;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.SieveSizeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class SieveSizeController {
  @Autowired
  private SieveSizeService sieveSizeService;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(SieveSizeController.class);

  @PostMapping(value = EndpointURI.SIEVE_SIZE)
  public ResponseEntity<Object> createSieveSize(
      @Valid @RequestBody List<SieveSizeRequestDto> sieveSizeRequestDtoList) {
    for (SieveSizeRequestDto sieveSizeRequestDto : sieveSizeRequestDtoList) {
      sieveSizeService.saveSieveSize(mapper.map(sieveSizeRequestDto, SieveSize.class));
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SIEVE_SIZE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_SIZES)
  public ResponseEntity<Object> getSieveSizes() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_SIZES,
        mapper.map(sieveSizeService.getAllSieveSizes(), SieveSizeResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SIEVE_SIZE_BY_ID)
  public ResponseEntity<Object> getSieveSizeById(@PathVariable Long id) {
    if (sieveSizeService.isSieveSizeExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SIEVE_SIZES,
          mapper.map(sieveSizeService.getSieveSizeById(id), SieveSizeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Sieve Size record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_SIZE_ID,
        validationFailureStatusCodes.getSieveSizeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.SIEVE_SIZE)
  public ResponseEntity<Object> updateSieveSize(
      @Valid @RequestBody SieveSizeRequestDto sieveSizeRequestDto) {
    if (sieveSizeService.isSieveSizeExist(sieveSizeRequestDto.getId())) {
      sieveSizeService.saveSieveSize(mapper.map(sieveSizeRequestDto, SieveSize.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SIEVE_SIZE_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Sieve Size record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_SIZE_ID,
        validationFailureStatusCodes.getSieveSizeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.SIEVE_SIZE_BY_ID)
  public ResponseEntity<Object> deleteSieveSize(@PathVariable Long id) {
    if (sieveSizeService.isSieveSizeExist(id)) {
      sieveSizeService.deleteSieveSize(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_SIEVE_SIZE_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Sieve Size record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_SIZE_ID,
        validationFailureStatusCodes.getSieveSizeNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SIEVE_SIZE_BY_MATERIAL_SUB_CATEGORY_ID)
  public ResponseEntity<Object> getSieveSizeByMaterialSubCategoryId(
      @PathVariable Long materialSubCategoryId) {
    if (materialSubCategoryService.isMaterialSubCategoryExist(materialSubCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_SUB_CATEGORY_ID,
          mapper.map(sieveSizeService.findByMaterialSubCategoryId(materialSubCategoryId),
              SieveSizeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Sieve Size record exist for given Material Sub Category id");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY_ID,
              validationFailureStatusCodes.getMaterialSubCategoryNotExist()),
          HttpStatus.BAD_REQUEST);
    }
  }
}

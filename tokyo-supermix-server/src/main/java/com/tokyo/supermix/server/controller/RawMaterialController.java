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
import com.tokyo.supermix.data.dto.RawMaterialRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.RawMaterialService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class RawMaterialController {
  @Autowired
  private RawMaterialService rawMaterialService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(RawMaterialController.class);

  @PostMapping(value = EndpointURI.RAW_MATERIAL)
  public ResponseEntity<Object> createRawMaterial(
      @Valid @RequestBody RawMaterialRequestDto rawMaterialRequestDto) {
    if (rawMaterialService.isRawMaterialNameExist(rawMaterialRequestDto.getName())) {
      logger.debug("Material already exists: createMaterial(), materialName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_NAME,
          validationFailureStatusCodes.getRawMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    RawMaterial rawMaterial = mapper.map(rawMaterialRequestDto, RawMaterial.class);
    rawMaterialService.saveRawMaterial(rawMaterial);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_RAW_MATERIAL_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.RAW_MATERIALS)
  public ResponseEntity<Object> getAllRawMaterials() {
    List<RawMaterialResponseDto> rawMaterialResponseDtoList =
        mapper.map(rawMaterialService.getAllRawMaterials(), RawMaterialResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        rawMaterialResponseDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_RAW_MATERIAL_BY_ID)
  public ResponseEntity<Object> getRawMaterialById(@PathVariable Long id) {
    if (rawMaterialService.isRawMaterialExist(id)) {
      RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.RAW_MATERIAL,
              mapper.map(rawMaterial, RawMaterialResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.RAW_MATERIAL)
  public ResponseEntity<Object> updateRawMaterial(
      @Valid @RequestBody RawMaterialRequestDto rawMaterialRequestDto) {
    if (rawMaterialService.isRawMaterialExist(rawMaterialRequestDto.getId())) {
      if (rawMaterialService.isUpdatedNameExist(rawMaterialRequestDto.getId(),
          rawMaterialRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_NAME,
            validationFailureStatusCodes.getRawMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      RawMaterial rawMaterial = mapper.map(rawMaterialRequestDto, RawMaterial.class);
      rawMaterialService.saveRawMaterial(rawMaterial);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_RAW_MATERIAL_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.DELETE_RAW_MATERIAL)
  public ResponseEntity<Object> deleteRawMaterial(@PathVariable Long id) {
    if (rawMaterialService.isRawMaterialExist(id)) {
      rawMaterialService.deleteRawMaterial(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_RAW_MATERIAL_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Raw Material record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.RAW_MATERIAL_ID,
        validationFailureStatusCodes.getRawMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

}
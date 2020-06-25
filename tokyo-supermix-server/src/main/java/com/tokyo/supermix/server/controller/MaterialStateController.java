package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.MaterialStateDto;
import com.tokyo.supermix.data.entities.MaterialState;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialStateService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;


@RestController
@CrossOrigin
public class MaterialStateController {
  @Autowired
  private MaterialStateService materialStateService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(MaterialStateController.class);

  @PostMapping(value = EndpointURI.MATERIAL_STATE)
  public ResponseEntity<Object> createMaterialState(
      @Valid @RequestBody MaterialStateDto materialStateDto) {
    if (materialStateService.isMaterialStateExist(materialStateDto.getMaterialState())) {
      logger.debug("Material State already exists: createMaterialState(), materialState: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_STATE,
          validationFailureStatusCodes.getMaterialStateAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    materialStateService.saveMaterialState(mapper.map(materialStateDto, MaterialState.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_STATE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_STATES)
  public ResponseEntity<Object> getAllMaterialStates() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_STATE,
        mapper.map(materialStateService.getAllMaterialStates(), MaterialStateDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_STATE_BY_ID)
  public ResponseEntity<Object> getMaterialStateById(@PathVariable Long id) {
    if (materialStateService.isMaterialStateExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_STATE,
          mapper.map(materialStateService.getMaterialStateById(id), MaterialStateDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Material State record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_STATE_ID,
        validationFailureStatusCodes.getMaterialStateNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_STATE)
  public ResponseEntity<Object> updateMaterialState(
      @Valid @RequestBody MaterialStateDto materialStateDto) {
    if (materialStateService.isMaterialStateExist(materialStateDto.getId())) {
      if (materialStateService.isUpdatedMaterialStateExist(materialStateDto.getId(),
          materialStateDto.getMaterialState())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_STATE,
                validationFailureStatusCodes.getMaterialStateAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      materialStateService.saveMaterialState(mapper.map(materialStateDto, MaterialState.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MATERIAL_STATE_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Material State record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_STATE_ID,
        validationFailureStatusCodes.getMaterialStateNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.MATERIAL_STATE_BY_ID)
  public ResponseEntity<Object> deleteMaterialState(@PathVariable Long id) {
    if (materialStateService.isMaterialStateExist(id)) {
      materialStateService.deleteMaterialState(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_MATERIAL_STATE_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Material State record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_STATE_ID,
        validationFailureStatusCodes.getMaterialStateNotExist()), HttpStatus.BAD_REQUEST);
  }
}


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
import com.tokyo.supermix.data.dto.MaterialAcceptedValueRequestDto;
import com.tokyo.supermix.data.dto.MaterialAcceptedValueResponseDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialAcceptedValueService;
import com.tokyo.supermix.server.services.TestConfigureService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class MaterialAcceptedValueController {

  @Autowired
  private MaterialAcceptedValueService materialAcceptedValueService;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MaterialAcceptedValueController.class);

  @PostMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE)
  public ResponseEntity<Object> createMaterialAcceptedValue(
      @Valid @RequestBody List<MaterialAcceptedValueRequestDto> materialAcceptedValueRequestDtoList) {
    for (MaterialAcceptedValueRequestDto materialAcceptedValueRequestDto : materialAcceptedValueRequestDtoList) {
      if (materialAcceptedValueService.isDuplicateEntryExist(
          materialAcceptedValueRequestDto.getTestConfigureId(),
          materialAcceptedValueRequestDto.getRawMaterialId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE,
                validationFailureStatusCodes.getAcceptedValueTestIdAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
    }
    materialAcceptedValueService.saveAcceptedValue(
        mapper.map(materialAcceptedValueRequestDtoList, MaterialAcceptedValue.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_MATERIAL_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUES)
  public ResponseEntity<Object> getAllMaterialAcceptedValues() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_ACCEPTED_VALUES,
            mapper.map(materialAcceptedValueService.getAllMaterialAcceptedValues(),
                MaterialAcceptedValueResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> getMaterialAcceptedValueById(@PathVariable Long id) {
    if (materialAcceptedValueService.isMaterialAcceptedValueExist(id)) {
      logger.debug("Get AcceptedValue by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.MATERIAL_ACCEPTED_VALUE,
              mapper.map(materialAcceptedValueService.getMaterialAcceptedValueById(id),
                  MaterialAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_ID)
  public ResponseEntity<Object> deleteAcceptedValue(@PathVariable Long id) {
    if (materialAcceptedValueService.isMaterialAcceptedValueExist(id)) {
      materialAcceptedValueService.deleteMaterialAcceptedValue(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MATERIAL_ACCEPTED_VALUE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid acceptedValueId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE_ID,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE)
  public ResponseEntity<Object> updateMaterialAcceptedValue(
      @Valid @RequestBody MaterialAcceptedValueRequestDto materialAcceptedValueRequestDto) {
    if (materialAcceptedValueService
        .isMaterialAcceptedValueExist(materialAcceptedValueRequestDto.getId())) {
      if (materialAcceptedValueService.isUpdatedRawMaterialIdExist(
          materialAcceptedValueRequestDto.getId(),
          materialAcceptedValueRequestDto.getRawMaterialId())) {
        materialAcceptedValueService.updateMaterialAcceptedValue(
            mapper.map(materialAcceptedValueRequestDto, MaterialAcceptedValue.class));
      }
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_ACCEPTED_VALUE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_ACCEPTED_VALUE,
        validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID)
  public ResponseEntity<Object> getMaterialAcceptedValueByTestConfigureId(
      @PathVariable Long testConfigureId) {
    if (testConfigureService.isTestConfigureExist(testConfigureId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.TEST_CONFIGURE,
              mapper.map(
                  materialAcceptedValueService.getMaterialAcceptedValueByTestConfigure(
                      testConfigureService.getTestConfigureById(testConfigureId)),
                  MaterialAcceptedValueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    } else {
      logger.debug("No AcceptedValue record exist for given Test type id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_CONFIGURE_ID,
          validationFailureStatusCodes.getAcceptedValueNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
}

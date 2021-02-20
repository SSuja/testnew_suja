package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
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
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.dto.MaterialQualityParameterResponseDto;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialQualityParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialQualityParameterController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private MaterialQualityParameterService materialQualityParameterService;

  @PostMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER)
  public ResponseEntity<Object> saveQualityParameter(
      @Valid @RequestBody List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    if (materialQualityParameterService
        .checkCommonNullFieldValues(materialQualityParameterRequestDtoList)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETERS,
              validationFailureStatusCodes.getCommonFieldsNotExist()),
          HttpStatus.BAD_REQUEST);
    }
    if (materialQualityParameterService
        .checkValidationByType(materialQualityParameterRequestDtoList)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_TYPES,
              validationFailureStatusCodes.getMaterialQualityParameterTypesNotExist()),
          HttpStatus.BAD_REQUEST);
    }
    if (materialQualityParameterService
        .checkValidationForConditionalRange(materialQualityParameterRequestDtoList)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_CONDITION,
              validationFailureStatusCodes.getMaterialQualityConditionRangesNotExist()),
          HttpStatus.BAD_REQUEST);
    }
    if (materialQualityParameterService
        .checkDuplicateEntry(materialQualityParameterRequestDtoList)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
              validationFailureStatusCodes.getMaterialQualityParameterAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    materialQualityParameterService.saveAllMaterialQualityParameter(
        mapper.map(materialQualityParameterRequestDtoList, MaterialQualityParameter.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_MATERIAL_QUALITY_PARAMETER_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETERS)
  public ResponseEntity<Object> getAllMaterialQualityParameters() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETERS,
            mapper.map(materialQualityParameterService.getAllMaterialQualityParameters(),
                MaterialQualityParameterResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_ID)
  public ResponseEntity<Object> deleteMaterialQualityParameterById(@PathVariable Long id) {
    if (materialQualityParameterService.isExistsById(id)) {
      materialQualityParameterService.deleteMaterialQualityParameterById(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_MATERIAL_QUALITY_PARAMETER_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
            validationFailureStatusCodes.getMaterialQualityParameterNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER)
  public ResponseEntity<Object> updateMaterialQualityParameter(
      @Valid @RequestBody MaterialQualityParameterRequestDto materialQualityParameterRequestDto) {
    if (materialQualityParameterService.isExistsById(materialQualityParameterRequestDto.getId())) {
      if (materialQualityParameterService
          .checkAlreadyExistsForUpdate(materialQualityParameterRequestDto)) {
        if (materialQualityParameterService
            .checkAlreadyExistsForUpdate(materialQualityParameterRequestDto)) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
                  validationFailureStatusCodes.getMaterialQualityParameterAlreadyExists()),
              HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
                validationFailureStatusCodes.getMaterialQualityParameterAlreadyExists()),
            HttpStatus.BAD_REQUEST);
      }
      if (materialQualityParameterService
          .checkValidationForConditionalRangeUpdate(materialQualityParameterRequestDto)) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_CONDITION,
                validationFailureStatusCodes.getMaterialQualityConditionRangesNotExist()),
            HttpStatus.BAD_REQUEST);
      }
      materialQualityParameterService.updateMaterialQualityParameter(
          mapper.map(materialQualityParameterRequestDto, MaterialQualityParameter.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MATERIAL_QUALITY_PARAMETER_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
            validationFailureStatusCodes.getMaterialQualityParameterNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_RAWMATERIAL)
  public ResponseEntity<Object> getMaterialParametersByRawMaterial(
      @PathVariable Long rawMaterialId) {
    if (materialQualityParameterService.existsMaterialParametersByRawMaterial(rawMaterialId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETERS,
              mapper.map(materialQualityParameterService.getAllMaterialParametersByRawMaterial(
                  rawMaterialId), MaterialQualityParameterResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
            validationFailureStatusCodes.getMaterialQualityParameterNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_SUBCATEGORY)
  public ResponseEntity<Object> getMaterialParametersBySubCategory(
      @PathVariable Long subCategoryId) {
    if (materialQualityParameterService.existsMaterialParametersBySubCategory(subCategoryId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETERS,
              mapper.map(materialQualityParameterService.getAllMaterialParametersBySubCategory(
                  subCategoryId), MaterialQualityParameterResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
            validationFailureStatusCodes.getMaterialQualityParameterNotExist()),
        HttpStatus.BAD_REQUEST);
  }
}

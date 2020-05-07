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
import com.tokyo.supermix.data.dto.CubeTestFindingRequestDto;
import com.tokyo.supermix.data.dto.CubeTestFindingResponseDto;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteStrengthTestService;
import com.tokyo.supermix.server.services.CubeTestFindingService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class CubeTestFindingController {
  @Autowired
  private Mapper mapper;
  @Autowired
  ConcreteStrengthTestService concreteStrengthTestService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private CubeTestFindingService cubeTestFindingService;
  private static final Logger logger = Logger.getLogger(CubeTestFindingController.class);

  @PostMapping(value = EndpointURI.CUBE_TEST_FINDING)
  public ResponseEntity<Object> saveCubeTestFinding(
      @Valid @RequestBody List<CubeTestFindingRequestDto> cubeTestFindingRequestDtoList) {
    for (CubeTestFindingRequestDto cubeTestFindingRequestDto : cubeTestFindingRequestDtoList) {
      if (cubeTestFindingService.checkAge(cubeTestFindingRequestDto.getAge())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUBE_TEST_FINDING_AGE,
            validationFailureStatusCodes.getCubeTestFindingAgeValid()), HttpStatus.BAD_REQUEST);
      }
      cubeTestFindingService
          .saveCubeTestFinding(mapper.map(cubeTestFindingRequestDto, CubeTestFinding.class));
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CUBE_TEST_FINDING_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CUBE_TEST_FINDINGS)
  public ResponseEntity<Object> getAllCubeTestFindings() {
    List<CubeTestFindingResponseDto> cubeTestFindingResponseDtoList = mapper
        .map(cubeTestFindingService.getAllCubeTestFindings(), CubeTestFindingResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.CUBE_TEST_FINDINGS,
        cubeTestFindingResponseDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CUBE_TEST_FINDING_BY_ID)
  public ResponseEntity<Object> getCubeTestFindingById(@PathVariable Long id) {
    if (cubeTestFindingService.isCubeTestFindingExist(id)) {
      logger.debug("Get cubeTestFinding By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CUBE_TEST_FINDING, mapper
          .map(cubeTestFindingService.getCubeTestFindingById(id), CubeTestFindingResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUBE_TEST_FINDING_ID,
        validationFailureStatusCodes.getCubeTestFindingNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CUBE_TEST_FINDING_BY_ID)
  public ResponseEntity<Object> deleteCubeTestFinding(@PathVariable Long id) {
    if (cubeTestFindingService.isCubeTestFindingExist(id)) {
      cubeTestFindingService.deleteCubeTestFinding(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CUBE_TEST_FINDING_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUBE_TEST_FINDING_ID,
        validationFailureStatusCodes.getCubeTestFindingNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CUBE_TEST_FINDING)
  public ResponseEntity<Object> updateCubeTestFinding(
      @Valid @RequestBody List<CubeTestFindingRequestDto> cubeTestFindingRequestDtoList) {
    for (CubeTestFindingRequestDto cubeTestFindingRequestDto : cubeTestFindingRequestDtoList) {
      if (cubeTestFindingService.isCubeTestFindingExist(cubeTestFindingRequestDto.getId())) {
        if (cubeTestFindingService.checkAge(cubeTestFindingRequestDto.getAge())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.CUBE_TEST_FINDING_AGE,
                  validationFailureStatusCodes.getCubeTestFindingAgeValid()),
              HttpStatus.BAD_REQUEST);
        }
        cubeTestFindingService
            .updateCubeTestFinding(mapper.map(cubeTestFindingRequestDto, CubeTestFinding.class));

      }
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CUBE_TEST_FINDING_SUCCESS),
          HttpStatus.OK);

    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUBE_TEST_FINDING_ID,
        validationFailureStatusCodes.getCubeTestFindingNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CUBE_TEST_FINDING_BY_FINISH_PRODUCT_SAMPLE_ID)
  public ResponseEntity<Object> getCubeTestFindingByFinishProductSampleId(
      @PathVariable Long finishProductSampleId) {
    if (cubeTestFindingService.existsByFinishProductSampleId(finishProductSampleId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ID,
          mapper.map(cubeTestFindingService.findByFinishProductSampleId(finishProductSampleId),
              CubeTestFindingResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No  record exist for given finish product id");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
              validationFailureStatusCodes.getFinishProductSampleNotExist()),
          HttpStatus.BAD_REQUEST);
    }
  }
}

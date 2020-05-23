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
import com.tokyo.supermix.data.dto.FinishProductSampleRequestDto;
import com.tokyo.supermix.data.dto.FinishProductSampleResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductSampleService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class FinishProductSampleController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private FinishProductSampleService finishProductSampleService;
  @Autowired
  private PlantService plantService;
  private static final Logger logger = Logger.getLogger(FinishProductSampleController.class);

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE)
  public ResponseEntity<Object> createFinishProductSample(
      @Valid @RequestBody FinishProductSampleRequestDto finishProductSampleRequestDto) {
    if (finishProductSampleService
        .isFinishProductCodeExist(finishProductSampleRequestDto.getFinishProductCode())) {
      logger.debug(
          "finish product sample code is already exists: createFinishProductSample(), isFinishProductCodeExist: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.FINISH_PRODUCT_CODE,
              validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    finishProductSampleService.saveFinishProductSample(
        mapper.map(finishProductSampleRequestDto, FinishProductSample.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINISH_PRODUCT_SAMPLE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLES)
  public ResponseEntity<Object> getAllFinishProductSamples() {
    logger.debug("get all finish product samples");
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
            mapper.map(finishProductSampleService.getAllFinishProductSamples(),
                FinishProductSampleResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_ID)
  public ResponseEntity<Object> getFinishProductSampleById(@PathVariable Long id) {
    if (finishProductSampleService.isFinishProductSampleExist(id)) {
      logger.debug("Get Finish Product Sample By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE,
          mapper.map(finishProductSampleService.getFinishProductSampleById(id),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_ID)
  public ResponseEntity<Object> deleteFinishProductSample(@PathVariable Long id) {
    if (finishProductSampleService.isFinishProductSampleExist(id)) {
      logger.debug("delete Finish Product Sample by id");
      finishProductSampleService.deleteFinishProductSample(id);;
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINISH_PRODUCT_SAMPLE_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
        validationFailureStatusCodes.getFinishProductSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE)
  public ResponseEntity<Object> updateFinishProductSample(
      @Valid @RequestBody FinishProductSampleRequestDto finishProductSampleRequestDto) {
    if (finishProductSampleService
        .isFinishProductSampleExist(finishProductSampleRequestDto.getId())) {
      if (finishProductSampleService.isUpdatedFinishProductCodeExist(
          finishProductSampleRequestDto.getId(),
          finishProductSampleRequestDto.getFinishProductCode())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.FINISH_PRODUCT_CODE,
                validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      finishProductSampleService.saveFinishProductSample(
          mapper.map(finishProductSampleRequestDto, FinishProductSample.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_SAMPLE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ID,
            validationFailureStatusCodes.getFinishProductSampleAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_MIX_DESIGN_CODE)
  public ResponseEntity<Object> getFinishProductSampleByMixDesignCode(
      @PathVariable String mixDesignCode) {
    if (finishProductSampleService.isMixDesignCodeExist(mixDesignCode)) {
      logger.debug("Get Finish Product Sample By Mix Design Code");
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(
              finishProductSampleService.getFinishProductSampleByMixDesignCode(mixDesignCode),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_CONCRETE_MIXER_ID)
  public ResponseEntity<Object> getFinishProductSampleByConcreteMixerId(
      @PathVariable Long concreteMixerId) {
    if (finishProductSampleService.isConcreteMixerExist(concreteMixerId)) {
      logger.debug("Get Finish Product Sample By Concrete Mixer Id");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
              mapper.map(finishProductSampleService.getFinishProductSampleByConcreteMixerId(
                  concreteMixerId), FinishProductSampleResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_MIXER_ID,
        validationFailureStatusCodes.getConcreteMixerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_SEARCH)
  public ResponseEntity<Object> getFinishProductSampleSearch(
      @QuerydslPredicate(root = FinishProductSample.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
        finishProductSampleService.searchFinishProductSample(predicate, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_BY_PLANT_CODE)
  public ResponseEntity<Object> getFinishProductSampleByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleService.getByPlantCode(plantCode),
              FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}

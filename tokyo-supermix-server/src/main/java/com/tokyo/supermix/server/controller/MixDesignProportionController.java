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
import com.tokyo.supermix.data.dto.MixDesignProportionRequestDto;
import com.tokyo.supermix.data.dto.MixDesignProportionResponseDto;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MixDesignProportionService;
import com.tokyo.supermix.server.services.MixDesignService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class MixDesignProportionController {
  @Autowired
  MixDesignProportionService mixDesignProportionService;

  @Autowired
  MixDesignService mixDesignService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MixDesignProportionController.class);

  @PostMapping(value = EndpointURI.MIXDESIGN_PROPORTION)
  public ResponseEntity<Object> saveMixDesignProportion(
      @Valid @RequestBody List<MixDesignProportionRequestDto> mixDesignProportionRequestDtoList) {
    mixDesignProportionRequestDtoList.stream().forEach(
        mixDesignProportionRequestDtoObj -> mixDesignProportionService.saveMixDesignProportion(
            mapper.map(mixDesignProportionRequestDtoObj, MixDesignProportion.class)));
    return new ResponseEntity<Object>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIXDESIGN_PROPORTION_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MIXDESIGN_PROPORTIONS)
  public ResponseEntity<Object> getAllMixDesignProportions() {
    List<MixDesignProportion> mixDesignProportionList =
        mixDesignProportionService.getAllMixDesignProportions();
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.MIXDESIGN_PROPORTIONS,
        mapper.map(mixDesignProportionList, MixDesignProportionResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);

  }

  @DeleteMapping(value = EndpointURI.MIXDESIGN_PROPORTION_BY_ID)
  public ResponseEntity<Object> deleteMixDesignPropotion(@PathVariable Long id) {
    if (mixDesignProportionService.isMixDesignProportionExist(id)) {
      mixDesignProportionService.deleteById(id);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIXDESIGN_PROPORTION_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIXDESIGN_DELETED,
            validationFailureStatusCodes.getMixDesignProportionAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MIXDESIGN_PROPORTION_BY_ID)
  public ResponseEntity<Object> getMixDesignProportionById(@PathVariable Long id) {
    if (mixDesignProportionService.isMixDesignProportionExist(id)) {
      logger.debug("Get mix design proportion by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIXDESIGN_PROPORTION_ID,
          mapper.map(mixDesignProportionService.getMixDesignProportionById(id),
              MixDesignProportionResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIXDESIGN_PROPORTION_ID,
        validationFailureStatusCodes.getMixDesignProportionNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIXDESIGN_PROPORTION)
  public ResponseEntity<Object> updateMixDesignProportion(
      @Valid @RequestBody MixDesignProportionRequestDto mixDesignProportionRequestDto) {
    if (mixDesignProportionService
        .isMixDesignProportionExist(mixDesignProportionRequestDto.getId())) {
      mixDesignProportionService.saveMixDesignProportion(
          mapper.map(mixDesignProportionRequestDto, MixDesignProportion.class));
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIXDESIGN_PROPORTION_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIXDESIGN_PROPORTION,
            validationFailureStatusCodes.getMixDesignProportionAlreadyExist()),
        HttpStatus.BAD_REQUEST);

  }

  @GetMapping(value = EndpointURI.MIXDESIGN_PROPORTION_BY_MIXDESIGNCODE)
  public ResponseEntity<Object> getMixDesignProportionByMixDesignCode(
      @PathVariable String mixDesignCode) {
    logger.debug("Get mix design proportion by id ");
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MIXDESIGN_PROPORTION_ID,
            mixDesignProportionService.findByMixDesign(
                mixDesignService.getMixDesignById(mixDesignCode)),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

}

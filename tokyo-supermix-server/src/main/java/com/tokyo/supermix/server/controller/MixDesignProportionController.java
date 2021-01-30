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
import org.springframework.web.bind.annotation.RequestParam;
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
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class MixDesignProportionController {
  @Autowired
  MixDesignProportionService mixDesignProportionService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MixDesignProportionController.class);

  @PostMapping(value = EndpointURI.MIX_DESIGN_PROPORTION)
  public ResponseEntity<Object> saveMixDesignProportion(
      @Valid @RequestBody List<MixDesignProportionRequestDto> mixDesignProportionRequestDtoList) {
    if (mixDesignProportionService
        .checkMixDesignProportionQuantityHasZeroValues(mixDesignProportionRequestDtoList)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MIX_DESIGN_PROPORTION,
              validationFailureStatusCodes.getMixDesignProportionHasZeroValue()),
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PROPORTION,
        mixDesignProportionService.saveMixDesignProportion(
            mapper.map(mixDesignProportionRequestDtoList, MixDesignProportion.class)),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PROPORTIONS)
  public ResponseEntity<Object> getAllMixDesignProportions() {
    return new ResponseEntity<Object>(
        new ContentResponse<>(Constants.MIX_DESIGN_PROPORTIONS,
            mapper.map(mixDesignProportionService.getAllMixDesignProportions(),
                MixDesignProportionResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.MIX_DESIGN_PROPORTION_BY_ID_DELETE)
  public ResponseEntity<Object> deleteMixDesignProportion(@PathVariable Long id,
      @PathVariable String mixDesignCode) {
    if (mixDesignProportionService.isMixDesignProportionExist(id)) {
      if (mixDesignProportionService.deleteProportionCheck(id, mixDesignCode)) {
        return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
            Constants.MIX_DESIGN_PROPORTION_DELETED_BAD), HttpStatus.OK);
      }
      mixDesignProportionService.deleteById(id);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_PROPORTION_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIX_DESIGN_PROPORTION_ID,
            validationFailureStatusCodes.getMixDesignProportionAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PROPORTION_BY_ID)
  public ResponseEntity<Object> getMixDesignProportionById(@PathVariable Long id) {
    if (mixDesignProportionService.isMixDesignProportionExist(id)) {
      logger.debug("Get mix design proportion by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PROPORTION_ID,
          mapper.map(mixDesignProportionService.getMixDesignProportionById(id),
              MixDesignProportionResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PROPORTION_ID,
        validationFailureStatusCodes.getMixDesignProportionNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIX_DESIGN_PROPORTION)
  public ResponseEntity<Object> updateMixDesignProportion(
      @Valid @RequestBody MixDesignProportionRequestDto mixDesignProportionRequestDto) {
    if (mixDesignProportionService
        .isMixDesignProportionExist(mixDesignProportionRequestDto.getId())) {
      mixDesignProportionService.updateMixDesignProportion(
          mapper.map(mixDesignProportionRequestDto, MixDesignProportion.class));
      return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MIX_DESIGN_PROPORTION_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIX_DESIGN_PROPORTION,
            validationFailureStatusCodes.getMixDesignProportionAlreadyExist()),
        HttpStatus.BAD_REQUEST);

  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PROPORTION_BY_MIX_DESIGN_CODE)
  public ResponseEntity<Object> getMixDesignProportionByMixDesignCode(
      @PathVariable String mixDesignCode) {
    if (mixDesignProportionService.existsByMixDesignCode(mixDesignCode)) {
      return new ResponseEntity<Object>(
          new ContentResponse<>(Constants.MIX_DESIGN_PROPORTIONS,
              mapper.map(mixDesignProportionService.findByMixDesignCode(mixDesignCode),
                  MixDesignProportionResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PROPORTION_SEARCH)
  public ResponseEntity<Object> getMixDesignPropotionBySearch(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "rawMaterialName1", required = false) String rawMaterialName1,
      @RequestParam(name = "rawMaterialName2", required = false) String rawMaterialName2,
      @RequestParam(name = "rawMaterialName3", required = false) String rawMaterialName3,
      @RequestParam(name = "rawMaterialName4", required = false) String rawMaterialName4,
      @RequestParam(name = "rawMaterialName5", required = false) String rawMaterialName5,
      @RequestParam(name = "mixDesignCode", required = false) String mixDesignCode) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PROPORTIONS,
        mixDesignProportionService.searchMixDesignProportion(rawMaterialName1, rawMaterialName2,
            rawMaterialName3, rawMaterialName4, rawMaterialName5, page, size, mixDesignCode),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.MixDesignParameterElementRequestDto;
import com.tokyo.supermix.data.dto.MixDesignParameterElementResponseDto;
import com.tokyo.supermix.data.entities.MixDesignParameterElement;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MixDesignParameterElementService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignParameterElementController {
  @Autowired
  private MixDesignParameterElementService mixDesignParameterElementService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(MixDesignParameterElementController.class);

  @PostMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_ELEMENT)
  public ResponseEntity<Object> createMixDesignParameterElement(
      @RequestBody MixDesignParameterElementRequestDto mixDesignParameterElementRequestDto) {
    if (mixDesignParameterElementService.isDuplicateEntryExists(
        mixDesignParameterElementRequestDto.getMixDesignParameterId(),
        mixDesignParameterElementRequestDto.getRawMaterialId())) {
      logger.debug(
          "Row is already exists: createMixDesignParameterElement(), isDuplicateRowExists: {}");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_ELEMENT,
              validationFailureStatusCodes.getMixDesignParameterElementAlreadyExit()),
          HttpStatus.BAD_REQUEST);
    }
    mixDesignParameterElementService.saveMixDesignParameterElement(
        mapper.map(mixDesignParameterElementRequestDto, MixDesignParameterElement.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_MIX_DESIGN_PARAMETER_ELEMENT_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_ELEMENTS)
  public ResponseEntity<Object> getParameterEquationElements() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER_ELEMENTS,
            mapper.map(mixDesignParameterElementService.getallMixDesignParameterElements(),
                MixDesignParameterElementResponseDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_ELEMENT_BY_ID)
  public ResponseEntity<Object> getMixDesignParameterElementById(@PathVariable Long id) {
    if (mixDesignParameterElementService.isMixDesignParameterElementExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER_ELEMENTS,
          mapper.map(mixDesignParameterElementService.getMixDesignParameterElementById(id),
              MixDesignParameterElementResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Mix Design Parameter Element record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_ELEMENT_ID,
            validationFailureStatusCodes.getMixDesignParameterElementNotExit()),
        HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.MIX_DESIGN_PARAMETER_ELEMENT_BY_ID)
  public ResponseEntity<Object> deleteMixDesignParameterElement(@PathVariable Long id) {
    if (mixDesignParameterElementService.isMixDesignParameterElementExist(id)) {
      mixDesignParameterElementService.deleteMixDesignParameterElement(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.DELETE_MIX_DESIGN_PARAMETER_ELEMENT_SCCESS), HttpStatus.OK);
    }
    logger.debug("No Mix Design Parameter record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_ELEMENT_ID,
            validationFailureStatusCodes.getMixDesignParameterElementNotExit()),
        HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_ELEMENT)
  public ResponseEntity<Object> updateMixDesignParameterElement(
      @RequestBody MixDesignParameterElementRequestDto mixDesignParameterElementRequestDto) {
    if (mixDesignParameterElementService
        .isMixDesignParameterElementExist(mixDesignParameterElementRequestDto.getId())) {
      if (mixDesignParameterElementService.isUpdateMixDesignParameter(
          mixDesignParameterElementRequestDto.getId(),
          mixDesignParameterElementRequestDto.getMixDesignParameterId())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_ELEMENT,
                validationFailureStatusCodes.getMixDesignParameterElementAlreadyExit()),
            HttpStatus.BAD_REQUEST);
      }
      mixDesignParameterElementService.saveMixDesignParameterElement(
          mapper.map(mixDesignParameterElementRequestDto, MixDesignParameterElement.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_MIX_DESIGN_PARAMETER_ELEMENT_SUCCESS), HttpStatus.OK);
    }
    logger.debug("No Mix Design Parameter Elemen record exist for given id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_ELEMENT_ID,
            validationFailureStatusCodes.getMixDesignParameterElementNotExit()),
        HttpStatus.BAD_REQUEST);
  }

}

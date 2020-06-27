package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.data.dto.ConcreteMixerRequestDto;
import com.tokyo.supermix.data.dto.ConcreteMixerResponseDto;
import com.tokyo.supermix.data.entities.ConcreteMixer;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.ConcreteMixerService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
@RestController
@CrossOrigin(origins = "*")
public class ConcreteMixerController {
  @Autowired
  private ConcreteMixerService concreteMixerService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(ConcreteMixerController.class);

  // create concrete mixer api
  @PostMapping(value = EndpointURI.CONCRETE_MIXER)
  public ResponseEntity<Object> createConcreteMixer(
      @Valid @RequestBody List<ConcreteMixerRequestDto> concreteMixerDtoList) {
    for (ConcreteMixerRequestDto concreteMixerDto : concreteMixerDtoList) {
      if (concreteMixerService.isNameNull(concreteMixerDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_MIXER,
                validationFailureStatusCodes.getConcreteMixerNameIsEmpty()),
            HttpStatus.BAD_REQUEST);
      }
      if (concreteMixerService.isDuplicateEntryExist(concreteMixerDto.getName(),
          concreteMixerDto.getPlantCode())) {
        logger.debug("Already Exists");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_MIXER,
                validationFailureStatusCodes.getConcreteMixerAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      concreteMixerService.saveConcreteMixer(mapper.map(concreteMixerDto, ConcreteMixer.class));
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_MIXER_SUCCESS),
        HttpStatus.OK);
  }

  // get all concrete mixer api
  @GetMapping(value = EndpointURI.CONCRETE_MIXERS)
  public ResponseEntity<Object> getAllConcreteMixers() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_MIXERS,
        mapper.map(concreteMixerService.getAllConcreteMixers(), ConcreteMixerResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  @GetMapping(value = EndpointURI.CONCRETE_MIXER_BY_PLANT)
  public ResponseEntity<Object> getAllConcreteMixersByPlant(@CurrentUser UserPrincipal currentUser) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_MIXERS,
        mapper.map(concreteMixerService.getAllConcreteMixersByPlant(currentUser), ConcreteMixerResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
  // delete concrete mixer api
  @DeleteMapping(value = EndpointURI.CONCRETE_MIXER_BY_ID)
  public ResponseEntity<Object> deleteConcreteMixer(@PathVariable Long id) {
    if (concreteMixerService.isConcreteMixerExist(id)) {
      concreteMixerService.deleteConcreteMixer(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_MIXER_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_MIXER,
        validationFailureStatusCodes.getConcreteMixerNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get concrete mixer by id api
  @GetMapping(value = EndpointURI.CONCRETE_MIXER_BY_ID)
  public ResponseEntity<Object> getConcreteMixerById(@PathVariable Long id) {
    if (concreteMixerService.isConcreteMixerExist(id)) {
      logger.debug("Id found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_MIXER,
          mapper.map(concreteMixerService.getConcreteMixerById(id), ConcreteMixerResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_MIXER,
        validationFailureStatusCodes.getConcreteMixerNotExist()), HttpStatus.BAD_REQUEST);
  }

  // update concrete mixer api
  @PutMapping(value = EndpointURI.CONCRETE_MIXER)
  public ResponseEntity<Object> updateConcreteMixer(
      @Valid @RequestBody ConcreteMixerRequestDto concreteMixerDto) {
    if (concreteMixerService.isConcreteMixerExist(concreteMixerDto.getId())) {
      if (concreteMixerService.isDuplicateEntryExist(concreteMixerDto.getName(),
          concreteMixerDto.getPlantCode())) {
        logger.debug("Already Exists");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CONCRETE_MIXER,
                validationFailureStatusCodes.getConcreteMixerAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      concreteMixerService.saveConcreteMixer(mapper.map(concreteMixerDto, ConcreteMixer.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CONCRETE_MIXER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_MIXER,
        validationFailureStatusCodes.getConcreteMixerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CONCRETE_MIXER_BY_PLANT_CODE)
  public ResponseEntity<Object> getConcreteMixerByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID, mapper
          .map(concreteMixerService.findByPlantCode(plantCode), ConcreteMixerResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Concrete Mixer record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.CONCRETE_MIXER_SEARCH)
  public ResponseEntity<Object> getConcreteMixerSearch(
      @QuerydslPredicate(root = ConcreteMixer.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_MIXERS,
        concreteMixerService.searchConcreteMixer(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }
}

package com.tokyo.supermix.server.controller;

import java.sql.Date;
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
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.dto.MixDesignRequestDto;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.MixDesignService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignController {

  @Autowired
  MixDesignService mixDesignService;
  @Autowired
  private PlantService plantService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(MixDesignController.class);

  @PostMapping(value = EndpointURI.MIX_DESIGN)
  public String saveMixDesign(@Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
    return mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
  }

  @GetMapping(value = EndpointURI.MIX_DESIGNS)
  public ResponseEntity<Object> getAllMixDesigns() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
        mapper.map(mixDesignService.getAllMixDesigns(), MixDesignResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.MIX_DESIGN_BY_CODE)
  public ResponseEntity<Object> deleteMixDesign(@PathVariable String code) {
    if (mixDesignService.isCodeExist(code)) {
      mixDesignService.deleteMixDesign(code);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_BY_CODE)
  public ResponseEntity<Object> getMixDesignById(@PathVariable String code) {
    if (mixDesignService.isCodeExist(code)) {
      logger.debug("Get mix design by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN,
          mapper.map(mixDesignService.getMixDesignByCode(code), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIX_DESIGN)
  public ResponseEntity<Object> updateMixDesign(
      @Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
    if (mixDesignService.isCodeExist(mixDesignRequestDto.getCode())) {
      mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MIX_DESIGN_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN,
        validationFailureStatusCodes.getMixDesignAlreadyExist()), HttpStatus.BAD_REQUEST);

  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_SEARCH)
  public ResponseEntity<Object> getMixDesignsBySearch(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "targetGrade", required = false) Double targetGrade,
      @RequestParam(name = "date", required = false) Date date,
      @RequestParam(name = "targetSlumpEqual", required = false) Double targetSlumpEqual,
      @RequestParam(name = "targetSlumpMin", required = false) Double targetSlumpMin,
      @RequestParam(name = "targetSlumpMax", required = false) Double targetSlumpMax,
      @RequestParam(name = "targetGradeEqual", required = false) Double targetGradeEqual,
      @RequestParam(name = "targetGradeMin", required = false) Double targetGradeMin,
      @RequestParam(name = "targetGradeMax", required = false) Double targetGradeMax,
      @RequestParam(name = "waterCementRatioEqual", required = false) Double waterCementRatioEqual,
      @RequestParam(name = "waterCementRatioMin", required = false) Double waterCementRatioMin,
      @RequestParam(name = "waterCementRatioMax", required = false) Double waterCementRatioMax,
      @RequestParam(name = "waterBinderRatioEqual", required = false) Double waterBinderRatioEqual,
      @RequestParam(name = "waterBinderRatioMin", required = false) Double waterBinderRatioMin,
      @RequestParam(name = "waterBinderRatioMax", required = false) Double waterBinderRatioMax,
      @RequestParam(name = "plantName", required = false) String plantName) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
        mixDesignService.searchMixDesign(targetSlumpMin, targetSlumpMax, targetSlumpEqual,
            targetGradeMin, targetGradeMax, targetGradeEqual, waterCementRatioMin,
            waterCementRatioMax, waterCementRatioEqual, waterBinderRatioMin, waterBinderRatioMax,
            waterBinderRatioEqual, plantName, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_BY_PLANT)
  public ResponseEntity<Object> getMixDesignByPlant(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID, mapper
          .map(mixDesignService.getMixDesignByPlantCode(plantCode), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No MixDesign record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_BY_STATUS)
  public ResponseEntity<Object> getMixDesignByStatus(@PathVariable Status status) {
    if (mixDesignService.isMixDesignStatusExist(status)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
          mapper.map(mixDesignService.getMixDesignByStatus(status), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No MixDesign record exist for given Status");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN,
          validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping(value = EndpointURI.MIX_DESIGN_BY_PLANT)
  public ResponseEntity<Object> getAllEmployees(@CurrentUser UserPrincipal currentUser,@PathVariable String plantCode) {
      if(currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MIX_DESIGN).contains(plantCode)) {
          return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
                    mapper.map(mixDesignService.getMixDesignByPlantCode(plantCode), MixDesignResponseDto.class),
                    RestApiResponseStatus.OK), null, HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
                validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}
